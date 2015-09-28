<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="athletes">
        <html>
            <head>
                <title>Decathlon results</title>
                <style type="text/css">
                    body {
                    font: normal medium/1.4 sans-serif;
                    }
                    table {
                    border-collapse: collapse;
                    width: 100%;
                    }
                    th, td {
                    padding: 0.25rem;
                    text-align: left;
                    border: 1px solid #ccc;
                    }
                    tbody tr:hover {
                    background: #ECE9E6;
                    }
                </style>
            </head>
            <body>
                <table border="1">
                    <tr>
                        <th>Position</th>
                        <th>Score</th>
                        <th>Name</th>
                        <th>Sprint 100m</th>
                        <th>Long jump</th>
                        <th>Shot put</th>
                        <th>High jump</th>
                        <th>Sprint 400m</th>
                        <th>Hurdles 110m</th>
                        <th>Discus throw</th>
                        <th>Pole vault</th>
                        <th>Javelin throw</th>
                        <th>Race 1500m</th>
                    </tr>
                    <xsl:apply-templates select="athlete"/>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="athlete">
        <tr>
            <td><xsl:value-of select="position"/></td>
            <td><xsl:value-of select="score"/></td>
            <td><xsl:value-of select="name"/></td>
            <td><xsl:value-of select="hundretMeters"/></td>
            <td><xsl:value-of select="longJump"/></td>
            <td><xsl:value-of select="shotPut"/></td>
            <td><xsl:value-of select="highJump"/></td>
            <td><xsl:value-of select="fourHundrets"/></td>
            <td><xsl:value-of select="hurdles"/></td>
            <td><xsl:value-of select="discusThrow"/></td>
            <td><xsl:value-of select="poleVault"/></td>
            <td><xsl:value-of select="javelinThrow"/></td>
            <td><xsl:value-of select="thousandFiveHundrets"/></td>
        </tr>
    </xsl:template>

</xsl:stylesheet>