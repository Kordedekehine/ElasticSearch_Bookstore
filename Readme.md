**ELASTICSEARCH CONFIGURATION (Local)**

**Prerequisites**

* Download the latest version of Elasticsearch from Elasticsearch Downloads.
* Unzip the downloaded file and add it to your system's PATH.
* Starting the Elasticsearch Server
* Open the Command Prompt as an administrator.
* Navigate to the Elasticsearch bin folder.
* Run the following command to start the server:

`elasticsearch.bat`


* Once the server starts, open your browser and go to localhost:9092.
* You will be prompted for a username and password.
* Resetting the Default Password
* If you need to reset the default password, follow these steps:

**Open Command Prompt as an administrator.**

Navigate to the Elasticsearch bin directory. For example:


`cd "C:\Program Files\elasticsearch-8.15.3\bin"`


Run the password reset command:

`elasticsearch-reset-password -u elastic --interactive`

**Enter a new password when prompted and confirm.**

**Note: Use the following credentials to log in on your browser:**

`Username: elastic (default)
Password: your new password (e.g., korede in this setup)`

**Generating a Truststore Certificate**

**If you encounter a keytool error, you may need to manually generate the truststore certificate:**

Open Command Prompt and run the following command:

`"C:\Program Files\elasticsearch-8.15.3\jdk\bin\
keytool.exe" -import -file "C:\Program Files\elasticsearch-8.15.3\config\certs\http_ca.crt" -keystore "C:\Program Files\elasticsearch-8.15.3\config\certs\truststore.p12" -storepass yourPassword -noprompt -storetype pkcs12
`
Replace yourPassword with the password you created.

Now you have access to Elasticsearch and can start working with it.


