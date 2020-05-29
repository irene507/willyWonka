<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
         <html>
            <head><title> This is your Warehouse</title></head>
            <body>
              <p><b>Name: <xsl:value-of select= "/warehouse/@name" /></b></p>
              <p>Corridor: <xsl:value-of select= "/warehouse/corridor" /></p>
              <p>Shelve: <xsl:value-of select= "/warehouse/shelve" /></p>
              </body>                  
         </html>






</xsl:template>
</xsl:stylesheet>