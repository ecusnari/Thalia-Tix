# Thalia Tix Theatre Web Service
Ticketing RESTful web service software for a hypothetical theatre that provides the API for presenting to a client shows, seats and price options, processing ticket orders, recording ticket donations, and compiling budget reports for the theatre staff. 

Table of contents
=================

<!--ts-->
   * [Getting Started](#getting-started)
      * [Prerequisites](#prerequisites)
      * [Installation](#installation)
      * [Deployment](#deployment)
   * [How to use](#how-to-use)
   * [Acknowledgements](#acknowledgements)
   * [License](#license)
<!--te-->


## Getting Started

#### Prerequisites

* OS: Ubuntu 16.04 or after
* [JRE](#install-jre)
* [Apache Tomcat 8.5](#install-tomcat-8.5)
* [Ant](#install-ant)

#### Installation

##### Install JRE
```
sudo add-apt-repository ppa:webupd8team/java

sudo apt-get update

sudo apt-get install oracle-java8-installer

sudo apt-get install oracle-java8-set-default
```

##### Install Tomcat 8.5
```
cd ~/Downloads

wget http://apache.claz.org/tomcat/tomcat-8/v8.5.23/bin/apache-tomcat-8.5.23.zip

unzip apache-tomcat-8.5.23.zip

cd apache-tomcat-8.5.23.zip/bin

chmod 744 *sh
```

##### Install Ant
`sudo apt-get install ant`

## Build and deploy instructions

##### Generate .war file
`git clone https://github.com/ecusnari/Thalia-Tix.git`

--> enter project directory "Thalia-Tix/ThaliaTixProject"

`ant build thaliatix war`

#### Deployment
move the genarated .war file to apache-tomcat-8.5.23.zip/webapps

start server:

```
cd apache-tomcat-8.5.23.zip/bin

chmod 744 *sh

./startup.sh
```

## How to use

url: localhost:8080/<"war file name">/....
- <"war file name"> for example if war file generated by ant is "Thalia.war", then the URL becomes: `http://localhost:8080/Thalia/[resource path]`
- for the [resource path] input the url of the resource like "thalia/shows", "thalia/orders" etc. So it becomes: `http://localhost:8080/Thalia/thalia/shows`

## Acknowledgements
* Professor Virgil Bistriceanu for providing us with all the guidence and API descriptions in his awesome Object Oriented Programming course at Illinois Institute of Technology
* Uncle Bob (Robert C. Martin) for the amazing Clean Code philosophy that I feel ashamed for not reading/watching before
* O'Reilly Media for the classic series on RESTful API book guides

## License
Apache license 2.0
