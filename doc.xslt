<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:template match="/">
   <html>
     <xsl:value-of select="unparsed-text('http://172.18.0.6:9200/')"/>
   </html>
 </xsl:template>
</xsl:stylesheet>
	