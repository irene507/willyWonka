<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
         <html>
            <head><title> This is your chocolate</title></head>
            <body>
              <p><b>Name: <xsl:value-of select= "/chocolate/@name" /></b></p>
              <p>Type:<xsl:value-of select= "/chocolate/@type" /></p>
              <p>Cocoa: <xsl:value-of select= "/chocolate/@cocoa" /></p>
              <p>Flavors:<xsl:value-of select= "/chocolate/@flavors" /></p>
              <p>Units:<xsl:value-of select= "/chocolate/@units" /></p>
              <p>Shape:<xsl:value-of select= "/chocolate/@shape" /></p>
              <p>Chocolate:
                  <table border="1"> 
                     <th>Chocolate</th>
                     <xsl: for-each select= "chocolate/name/name">
                     <tr>
                        <td><xsl:value-of select= "/name" /></tr></td>
                     </xsl: for-each>
                  </table>
               </p>
            </body>                  
         </html>






</xsl:template>
</xsl:stylesheet>