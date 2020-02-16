To compile and execute follow the steps
1. Place the "www" folder, "abc.jpg", "MyServer.java", "RequestProcessor.java" and "makefile" in same folder.
2. Execute the make command 
3. Compile the java programs by using javac MyServer.java RequestProcessor.java
4. Execute the java programs by using java MyServer RequestProcessor //Port No: 1253
5. Test the Server from different nodes, "notFound404.jpg" serves ar Error 404 incase if client tries to access without www
6. The implementation as been tested on both local and remote machines using both Firefox and wget.
7. Sample Output

//*********************************| Server |**************************************************//

Last login: Mon Sep 23 13:30:03 2019 from 149.125.65.216
sjain13@remote06:~$ cd DS
sjain13@remote06:~/DS$ cd A1
sjain13@remote06:~/DS/A1$ javac MyServer.java RequestProcessor.java
sjain13@remote06:~/DS/A1$ java MyServer RequestProcessor
Listening for connection
/abc.jpg |128.226.114.206|52034|1
/abc.jpg |128.226.114.206|52036|2
/test.html |128.226.114.200|36928|1
/test.html |128.226.114.200|36946|2
/abc.jpg |128.226.114.205|60254|3
/pdf-sample.pdf |128.226.114.206|52080|1
/pdf-sample.pdf |128.226.114.205|60258|2


//*******************************| Client 1 |********************************************//

sjain13@remote06:~/DS/A1$ wget http://remote06.cs.binghamton.edu:1253/abc.jpg
--2019-09-23 22:26:58--  http://remote06.cs.binghamton.edu:1253/abc.jpg
Resolving remote06.cs.binghamton.edu (remote06.cs.binghamton.edu)... 128.226.114.206
Connecting to remote06.cs.binghamton.edu (remote06.cs.binghamton.edu)|128.226.114.206|:1253... connected.
HTTP request sent, awaiting response... 200 OK
Length: 691718 (676K) [application/octet-stream]
Saving to: ‘abc.jpg.1’

abc.jpg.1           100%[===================>] 675.51K  --.-KB/s    in 0.001s

2019-09-23 22:26:58 (935 MB/s) - ‘abc.jpg.1’ saved [691718/691718]

sjain13@remote06:~/DS/A1$ wget http://remote06.cs.binghamton.edu:1253/pdf-sample.pdf
--2019-09-23 22:36:54--  http://remote06.cs.binghamton.edu:1253/pdf-sample.pdf
Resolving remote06.cs.binghamton.edu (remote06.cs.binghamton.edu)... 128.226.114.206
Connecting to remote06.cs.binghamton.edu (remote06.cs.binghamton.edu)|128.226.114.206|:1253... connected.
HTTP request sent, awaiting response... 200 OK
Length: 7945 (7.8K) [application/pdf]
Saving to: ‘pdf-sample.pdf.1’

pdf-sample.pdf.1    100%[===================>]   7.76K  --.-KB/s    in 0.01s

2019-09-23 22:36:54 (647 KB/s) - ‘pdf-sample.pdf.1’ saved [7945/7945]

//***************************| Client 2 |******************************//

sjain13@remote00:~/DS/A1$ wget http://remote06.cs.binghamton.edu:1253/test.html
--2019-09-23 22:32:20--  http://remote06.cs.binghamton.edu:1253/test.html
Resolving remote06.cs.binghamton.edu (remote06.cs.binghamton.edu)... 128.226.114.206
Connecting to remote06.cs.binghamton.edu (remote06.cs.binghamton.edu)|128.226.114.206|:1253... connected.
HTTP request sent, awaiting response... 200 OK
Length: 611 [text/html]
Saving to: ‘test.html.1’

test.html.1         100%[===================>]     611  --.-KB/s    in 0s

2019-09-23 22:32:20 (50.9 MB/s) - ‘test.html.1’ saved [611/611]

//*************************| Client 3 |*********************************//

Last login: Fri Mar  8 20:51:36 2019 from 198.255.144.121
sjain13@remote05:~$ cd DS
sjain13@remote05:~/DS$ cd A1
sjain13@remote05:~/DS/A1$ wget http://remote06.cs.binghamton.edu:1253/abc.jpg
--2019-09-23 22:32:20--  http://remote06.cs.binghamton.edu:1253/abc.jpg
Resolving remote06.cs.binghamton.edu (remote06.cs.binghamton.edu)... 128.226.114.206
Connecting to remote06.cs.binghamton.edu (remote06.cs.binghamton.edu)|128.226.114.206|:1253... connected.
HTTP request sent, awaiting response... 200 OK
Length: 691718 (676K) [application/octet-stream]
Saving to: ‘abc.jpg.2’

abc.jpg.2                       100%[======================================================>] 675.51K  --.-KB/s    in 0.001s

2019-09-23 22:32:20 (535 MB/s) - ‘abc.jpg.2’ saved [691718/691718]

sjain13@remote05:~/DS/A1$ wget http://www.cs.binghamton.edu/~yaoliu/courses/cs557/pdf-sample.pdf
--2019-09-23 22:35:37--  http://www.cs.binghamton.edu/~yaoliu/courses/cs557/pdf-sample.pdf
Resolving www.cs.binghamton.edu (www.cs.binghamton.edu)... 128.226.116.128, 128.226.116.131, 128.226.116.129
Connecting to www.cs.binghamton.edu (www.cs.binghamton.edu)|128.226.116.128|:80... connected.
HTTP request sent, awaiting response... 200 OK
Length: 7945 (7.8K) [application/pdf]
Saving to: ‘pdf-sample.pdf’

pdf-sample.pdf                  100%[======================================================>]   7.76K  --.-KB/s    in 0s

2019-09-23 22:35:37 (908 MB/s) - ‘pdf-sample.pdf’ saved [7945/7945]

sjain13@remote05:~/DS/A1$ wget http://remote06.cs.binghamton.edu:1253/pdf-sample.pdf
--2019-09-23 22:36:54--  http://remote06.cs.binghamton.edu:1253/pdf-sample.pdf
Resolving remote06.cs.binghamton.edu (remote06.cs.binghamton.edu)... 128.226.114.206
Connecting to remote06.cs.binghamton.edu (remote06.cs.binghamton.edu)|128.226.114.206|:1253... connected.
HTTP request sent, awaiting response... 200 OK
Length: 7945 (7.8K) [application/pdf]
Saving to: ‘pdf-sample.pdf.2’

pdf-sample.pdf.2                100%[======================================================>]   7.76K  --.-KB/s    in 0s

2019-09-23 22:36:54 (862 MB/s) - ‘pdf-sample.pdf.2’ saved [7945/7945]

8.All the response headers have been tested in Firefox.
