# MessagingService
## Messaging API to process product sales message.
	Required Tech stack build this application:
		1) JDK11
		2) MAVEN 3.5 or above
### How to run and test the application
	Go to root directory of message service api
		Build application using 
			mvn clean install
		Go inside target directory
		Execute below command to start the application
			java -jar messageprocess-1.0-SNAPSHOT.jar <message file path present inside project directory>
			Ex: java -jar messageprocess-1.0-SNAPSHOT.jar E:\MessagingService\messageFile.txt
		Put new data inside messageFile.txt file and that will process by the message service
			
### Message information 
	Inside messageFile.txt file
			PRODUCTTYPE,PRICE,UNIT,QUANTITY,ADJUSTMENT
		1)  MANGO,5,P,25 -> This is 1type of  message. It wil insert 5 Mango of price 25 each into the messge api
		2)  MANGO,2,P,0,SUBTRACT -> This is 2type of message. It wil adjust each mango price by subtract it with 
		Different adjustment values are :
			ADD,
			SUBTRACT,
			MULTIPLY
		Different price unit are :
			P,
			POUND --> As of not this unit is not in use


