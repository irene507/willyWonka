<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
         <html>
            <head><title> This is your animal!</title></head>
            <body>
              <p><b>Name: <xsl:value-of select= "/animal/@name" /></b></p>
              <p>Country:<xsl:value-of select= "/animal/country" /></p>
              <p>Color: <xsl:value-of select= "/animal/colour" /></p>
              <p>Specie:<xsl:value-of select= "/animal/specie" /></p>
              <p>Date of birth:<xsl:value-of select= "/animal/dob" /></p>
       
              
 
             </body>
             </html>

</xsl:template>
</xsl:stylesheet>