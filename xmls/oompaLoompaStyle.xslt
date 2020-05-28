<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
         <html>
            <head><title> This is your Oompa Loompa</title></head>
            <body>
              <p><b>Name: <xsl:value-of select= "/oompaloompa/@name" /></b></p>
              <p>Cellphone: <xsl:value-of select= "/oompaloompa/cellphone" /></p>
              <p>Email: <xsl:value-of select= "/oompaloompa/email" /></p>
              <p>Address: <xsl:value-of select= "/oompaloompa/address" /></p>
              <p>DOB: <xsl:value-of select= "/oompaloompa/dob" /></p>
              
            </body>                  
         </html>






</xsl:template>
</xsl:stylesheet>