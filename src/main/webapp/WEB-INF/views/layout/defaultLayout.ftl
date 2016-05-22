<#macro bidderLayout>
  <html>
  <head>
  	<link rel="stylesheet" type="text/css" href="../css/main.css" />
  </head>
    <body style="width:100%;height:100%">
      <table>
        <tr>
          <td colspan="2">
            <#include "header.ftl"/>
          </td>
        </tr>
        <tr>
          <td align="center">
            <#nested/>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <#include "footer.ftl"/>
          </td>
        </tr>
      </table>
    </body>
  </html>
</#macro>