import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class RequestProcessor extends Thread{
	private Socket socket = null;
	static HashMap<String, Integer> reqsMap = new HashMap<String, Integer>();

	public RequestProcessor(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		byte[] buffer = null;
		int numberOfBytes = 0;
		try {
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String line = br.readLine();

			String file = "";
			StringTokenizer stringTokenizer = new StringTokenizer(line);
			OutputStream out = socket.getOutputStream();
			try {
				file = updateReqsMap(file, stringTokenizer);
				writeToSocket(file, out);
			} catch (FileNotFoundException e) {
				Create404ErrorRes(out);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			} finally {
				in.close();
				socket.close();
				System.err.println(line.substring(line.indexOf(' ') + 1, line.indexOf("HTTP")) + "|"
						+ (((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress()).toString().replace("/",
								"")
						+ "|"
						+ socket.getRemoteSocketAddress().toString()
								.substring(socket.getRemoteSocketAddress().toString().lastIndexOf(':') + 1)
						+ "|" + reqsMap.get(file));
			}

		} catch (IOException e) {
			System.err.println("Error" + e.getMessage());
			System.exit(0);
		}
	}

	private void writeToSocket(String file, OutputStream out)
			throws FileNotFoundException, IOException, UnsupportedEncodingException, UnknownHostException {
		byte[] buffer;
		int numberOfBytes;
		InputStream inputStream = new FileInputStream("www/" + file);
		String mimeType = getMimeType(file);
		out.write("HTTP/1.1 200 OK\r\n".getBytes("UTF-8"));
		String dateformatter = DateTimeFormatter.RFC_1123_DATE_TIME.withZone(ZoneOffset.UTC)
				.format(Instant.now());
		String date = "Date: " + dateformatter + "\r\n";
		out.write(date.getBytes("UTF-8"));
		String server = "Server: " + InetAddress.getLocalHost().getHostName() + "\r\n";
		out.write(server.getBytes("UTF-8"));
		out.write("Accept-Ranges: bytes\r\n".getBytes("UTF-8"));
		File fileAccessed = new File("www/" + file);
		String contentLength = "Content-Length: " + fileAccessed.length() + "\r\n";
		out.write(contentLength.getBytes("UTF-8"));
		mimeType = "Content-Type: " + mimeType + "\r\n";
		out.write(mimeType.getBytes("UTF-8"));
		out.write("\r\n".getBytes("UTF-8"));
		buffer = new byte[4096];
		while ((numberOfBytes = inputStream.read(buffer)) != -1) {
			out.write(buffer, 0, numberOfBytes);
		}
	}

	private synchronized String updateReqsMap(String file, StringTokenizer stringTokenizer) throws FileNotFoundException {
		if (stringTokenizer.hasMoreElements() && stringTokenizer.nextToken().equalsIgnoreCase("GET")
				&& stringTokenizer.hasMoreElements()) {
			file = stringTokenizer.nextToken();
		} else {
			throw new FileNotFoundException();
		}
		if (file.endsWith("/"))
			file += "test.html";
		if (file.indexOf("/") == 0)
			file = file.substring(1);
		if (reqsMap.get(file) == null) {
			reqsMap.put(file, 1);
		} else {
			int count = reqsMap.get(file)+1; 
			reqsMap.put(file, count);
		}
		return file;
	}

	private void Create404ErrorRes(OutputStream out)
			throws IOException, UnsupportedEncodingException, FileNotFoundException {
		byte[] buffer;
		int numberOfBytes;
		out.write("HTTP/1.1 404 Not Found\r\nContent-type: image/jpeg\r\n\r\n".getBytes("UTF-8"));
		InputStream inputStream = new FileInputStream("notFound404.jpg");
		buffer = new byte[4096];
		while ((numberOfBytes = inputStream.read(buffer)) != -1) {
			out.write(buffer, 0, numberOfBytes);
		}
	}

	private String getMimeType(String file) {
		String mimeType = "application/octet-stream";
		if (file.endsWith(".html"))
			mimeType = "text/html";
		else if (file.endsWith(".gif"))
			mimeType = "image/gif";
		else if (file.endsWith(".pdf"))
			mimeType = "application/pdf";
		else if (file.endsWith(".deb"))
			mimeType = "application/x-debian-package";
		return mimeType;
	}
}
