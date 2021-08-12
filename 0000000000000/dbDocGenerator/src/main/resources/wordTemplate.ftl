<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<?mso-application progid="Word.Document"?>
<pkg:package xmlns:pkg="http://schemas.microsoft.com/office/2006/xmlPackage">
	<pkg:part pkg:name="/_rels/.rels"
		pkg:contentType="application/vnd.openxmlformats-package.relationships+xml">
		<pkg:xmlData>
			<Relationships
				xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
				<Relationship Id="rId4"
					Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument"
					Target="word/document.xml" />
				<Relationship Id="rId2"
					Type="http://schemas.openxmlformats.org/package/2006/relationships/metadata/core-properties"
					Target="docProps/core.xml" />
				<Relationship Id="rId1"
					Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/extended-properties"
					Target="docProps/app.xml" />
				<Relationship Id="rId3"
					Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/custom-properties"
					Target="docProps/custom.xml" />
			</Relationships>
		</pkg:xmlData>
	</pkg:part>
	<pkg:part pkg:name="/word/_rels/document.xml.rels"
		pkg:contentType="application/vnd.openxmlformats-package.relationships+xml">
		<pkg:xmlData>
			<Relationships
				xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
				<Relationship Id="rId5"
					Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/fontTable"
					Target="fontTable.xml" />
				<Relationship Id="rId4"
					Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/customXml"
					Target="../customXml/item1.xml" />
				<Relationship Id="rId3"
					Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/theme"
					Target="theme/theme1.xml" />
				<Relationship Id="rId2"
					Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/settings"
					Target="settings.xml" />
				<Relationship Id="rId1"
					Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/styles"
					Target="styles.xml" />
			</Relationships>
		</pkg:xmlData>
	</pkg:part>
	<pkg:part pkg:name="/word/document.xml"
		pkg:contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml">
		<pkg:xmlData>
			<w:document
				xmlns:wpc="http://schemas.microsoft.com/office/word/2010/wordprocessingCanvas"
				xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
				xmlns:o="urn:schemas-microsoft-com:office:office"
				xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
				xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math"
				xmlns:v="urn:schemas-microsoft-com:vml"
				xmlns:wp14="http://schemas.microsoft.com/office/word/2010/wordprocessingDrawing"
				xmlns:wp="http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing"
				xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
				xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
				xmlns:w10="urn:schemas-microsoft-com:office:word" xmlns:w15="http://schemas.microsoft.com/office/word/2012/wordml"
				xmlns:wpg="http://schemas.microsoft.com/office/word/2010/wordprocessingGroup"
				xmlns:wpi="http://schemas.microsoft.com/office/word/2010/wordprocessingInk"
				xmlns:wne="http://schemas.microsoft.com/office/word/2006/wordml"
				xmlns:wps="http://schemas.microsoft.com/office/word/2010/wordprocessingShape"
				xmlns:wpsCustomData="http://www.wps.cn/officeDocument/2013/wpsCustomData"
				mc:Ignorable="w14 w15 wp14">
				<w:body>
					<w:p>
						<w:pPr>
							<w:pStyle w:val="2" />
							<w:jc w:val="center" />
							<w:rPr>
								<w:rFonts w:hint="eastAsia" w:eastAsia="宋体" />
								<w:lang w:val="en-US" w:eastAsia="zh-CN" />
							</w:rPr>
						</w:pPr>
						<w:bookmarkStart w:id="0" w:name="_Toc31102" />
						<w:r>
							<w:rPr>
								<w:rFonts w:hint="eastAsia" />
								<w:lang w:val="en-US" w:eastAsia="zh-CN" />
							</w:rPr>
							<w:t>${databaseName}</w:t>
						</w:r>
						<w:bookmarkEnd w:id="0" />
					</w:p>
					<w:p>
						<w:pPr>
							<w:rPr>
								<w:b />
							</w:rPr>
						</w:pPr>
					</w:p>
					<w:p>
						<w:pPr>
							<w:rPr>
								<w:b />
							</w:rPr>
						</w:pPr>
					</w:p>
					<#list tableInfos as tableInfo>
					<w:p>
						<w:r>
							<w:rPr>
								<w:b />
							</w:rPr>
							<w:t>数据表名：</w:t>
						</w:r>
						<w:r>
							<w:rPr>
								<w:rFonts w:hint="eastAsia" />
								<w:b />
								<w:lang w:val="en-US" w:eastAsia="zh-CN" />
							</w:rPr>
							<w:t>${tableInfo.tableName}</w:t>
						</w:r>
						<w:r>
							<w:rPr>
								<w:b />
							</w:rPr>
							<w:t xml:space="preserve"> 注释： </w:t>
						</w:r>
						<w:r>
							<w:rPr>
								<w:rFonts w:hint="eastAsia" />
								<w:b />
								<w:lang w:val="en-US" w:eastAsia="zh-CN" />
							</w:rPr>
							<w:t>${tableInfo.tableRemark}</w:t>
						</w:r>
					</w:p>
					<w:tbl>
						<w:tblPr>
							<w:tblStyle w:val="5" />
							<w:tblW w:w="8525" w:type="dxa" />
							<w:tblInd w:w="0" w:type="dxa" />
							<w:tblBorders>
								<w:top w:val="single" w:color="auto" w:sz="0" w:space="0" />
								<w:left w:val="single" w:color="auto" w:sz="0" w:space="0" />
								<w:bottom w:val="single" w:color="auto" w:sz="0"
									w:space="0" />
								<w:right w:val="single" w:color="auto" w:sz="0" w:space="0" />
								<w:insideH w:val="single" w:color="auto" w:sz="0"
									w:space="0" />
								<w:insideV w:val="single" w:color="auto" w:sz="0"
									w:space="0" />
							</w:tblBorders>
							<w:tblLayout w:type="fixed" />
							<w:tblCellMar>
								<w:top w:w="0" w:type="dxa" />
								<w:left w:w="108" w:type="dxa" />
								<w:bottom w:w="0" w:type="dxa" />
								<w:right w:w="108" w:type="dxa" />
							</w:tblCellMar>
						</w:tblPr>
						<w:tblGrid>
							<w:gridCol w:w="961" />
							<w:gridCol w:w="836" />
							<w:gridCol w:w="1857" />
							<w:gridCol w:w="1535" />
							<w:gridCol w:w="1322" />
							<w:gridCol w:w="1007" />
							<w:gridCol w:w="1007" />
						</w:tblGrid>
						<w:tr>
							<w:tblPrEx>
								<w:tblBorders>
									<w:top w:val="single" w:color="auto" w:sz="0" w:space="0" />
									<w:left w:val="single" w:color="auto" w:sz="0" w:space="0" />
									<w:bottom w:val="single" w:color="auto" w:sz="0"
										w:space="0" />
									<w:right w:val="single" w:color="auto" w:sz="0"
										w:space="0" />
									<w:insideH w:val="single" w:color="auto" w:sz="0"
										w:space="0" />
									<w:insideV w:val="single" w:color="auto" w:sz="0"
										w:space="0" />
								</w:tblBorders>
								<w:tblLayout w:type="fixed" />
								<w:tblCellMar>
									<w:top w:w="0" w:type="dxa" />
									<w:left w:w="108" w:type="dxa" />
									<w:bottom w:w="0" w:type="dxa" />
									<w:right w:w="108" w:type="dxa" />
								</w:tblCellMar>
							</w:tblPrEx>
							<w:trPr>
								<w:trHeight w:val="283" w:hRule="atLeast" />
							</w:trPr>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="961" w:type="dxa" />
									<w:vAlign w:val="center" />
								</w:tcPr>
								<w:p>
									<w:r>
										<w:t>字段</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="836" w:type="dxa" />
									<w:vAlign w:val="center" />
								</w:tcPr>
								<w:p>
									<w:r>
										<w:t>类型</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1857" w:type="dxa" />
									<w:vAlign w:val="center" />
								</w:tcPr>
								<w:p>
									<w:r>
										<w:t>注释</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1535" w:type="dxa" />
									<w:vAlign w:val="center" />
								</w:tcPr>
								<w:p>
									<w:pPr>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" w:eastAsia="宋体" />
											<w:lang w:eastAsia="zh-CN" />
										</w:rPr>
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
										<w:t>键</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1322" w:type="dxa" />
									<w:vAlign w:val="center" />
								</w:tcPr>
								<w:p>
									<w:pPr>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" w:eastAsia="宋体" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
										<w:t>能否为空</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1007" w:type="dxa" />
									<w:vAlign w:val="center" />
								</w:tcPr>
								<w:p>
									<w:pPr>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
										<w:t>默认值</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1007" w:type="dxa" />
									<w:vAlign w:val="center" />
								</w:tcPr>
								<w:p>
									<w:pPr>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
										<w:t>其他</w:t>
									</w:r>
								</w:p>
							</w:tc>
						</w:tr>
						<#list tableInfo.fields as field>
						<w:tr>
							<w:tblPrEx>
								<w:tblBorders>
									<w:top w:val="single" w:color="auto" w:sz="0" w:space="0" />
									<w:left w:val="single" w:color="auto" w:sz="0" w:space="0" />
									<w:bottom w:val="single" w:color="auto" w:sz="0"
										w:space="0" />
									<w:right w:val="single" w:color="auto" w:sz="0"
										w:space="0" />
									<w:insideH w:val="single" w:color="auto" w:sz="0"
										w:space="0" />
									<w:insideV w:val="single" w:color="auto" w:sz="0"
										w:space="0" />
								</w:tblBorders>
								<w:tblLayout w:type="fixed" />
								<w:tblCellMar>
									<w:top w:w="0" w:type="dxa" />
									<w:left w:w="108" w:type="dxa" />
									<w:bottom w:w="0" w:type="dxa" />
									<w:right w:w="108" w:type="dxa" />
								</w:tblCellMar>
							</w:tblPrEx>
							<w:trPr>
								<w:trHeight w:val="458" w:hRule="atLeast" />
							</w:trPr>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="961" w:type="dxa" />
									<w:vAlign w:val="center" />
								</w:tcPr>
								<w:p>
									<w:pPr>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" w:eastAsia="宋体" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:sz w:val="11" />
											<w:szCs w:val="11" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
										<w:t>${field.field}</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="836" w:type="dxa" />
									<w:vAlign w:val="center" />
								</w:tcPr>
								<w:p>
									<w:pPr>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" w:eastAsia="宋体" />
											<w:lang w:eastAsia="zh-CN" />
										</w:rPr>
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:sz w:val="11" />
											<w:szCs w:val="11" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
										<w:t>${field.type}</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1857" w:type="dxa" />
									<w:vAlign w:val="center" />
								</w:tcPr>
								<w:p>
									<w:pPr>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" w:eastAsia="宋体" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:sz w:val="11" />
											<w:szCs w:val="11" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
										<w:t>${field.remark}</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1535" w:type="dxa" />
									<w:vAlign w:val="center" />
								</w:tcPr>
								<w:p>
									<w:pPr>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" w:eastAsia="宋体" />
											<w:lang w:eastAsia="zh-CN" />
										</w:rPr>
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:sz w:val="11" />
											<w:szCs w:val="11" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
										<w:t>${field.key}</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1322" w:type="dxa" />
									<w:vAlign w:val="center" />
								</w:tcPr>
								<w:p>
									<w:pPr>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:sz w:val="11" />
											<w:szCs w:val="11" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:sz w:val="11" />
											<w:szCs w:val="11" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
										<w:t>${field.nullAble}</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1007" w:type="dxa" />
									<w:vAlign w:val="center" />
								</w:tcPr>
								<w:p>
									<w:pPr>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:sz w:val="11" />
											<w:szCs w:val="11" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:sz w:val="11" />
											<w:szCs w:val="11" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
										<w:t>${field.defaultValue}</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1007" w:type="dxa" />
									<w:vAlign w:val="center" />
								</w:tcPr>
								<w:p>
									<w:pPr>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:sz w:val="11" />
											<w:szCs w:val="11" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
											<w:sz w:val="11" />
											<w:szCs w:val="11" />
											<w:lang w:val="en-US" w:eastAsia="zh-CN" />
										</w:rPr>
										<w:t>${field.extra}</w:t>
									</w:r>
								</w:p>
							</w:tc>
						</w:tr>
						</#list>
					</w:tbl>
					</#list>
					<w:p>
						<w:pPr>
							<w:rPr>
								<w:rFonts w:hint="eastAsia" w:eastAsia="宋体" />
								<w:lang w:val="en-US" w:eastAsia="zh-CN" />
							</w:rPr>
						</w:pPr>
						<w:bookmarkStart w:id="1" w:name="_GoBack" />
						<w:bookmarkEnd w:id="1" />
					</w:p>
					<w:sectPr>
						<w:pgSz w:w="11906" w:h="16838" />
						<w:docGrid w:type="lines" w:linePitch="312" w:charSpace="0" />
					</w:sectPr>
				</w:body>
			</w:document>
		</pkg:xmlData>
	</pkg:part>
	<pkg:part pkg:name="/customXml/_rels/item1.xml.rels"
		pkg:contentType="application/vnd.openxmlformats-package.relationships+xml">
		<pkg:xmlData>
			<Relationships
				xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
				<Relationship Id="rId1"
					Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/customXmlProps"
					Target="itemProps1.xml" />
			</Relationships>
		</pkg:xmlData>
	</pkg:part>
	<pkg:part pkg:name="/customXml/item1.xml" pkg:contentType="application/xml">
		<pkg:xmlData>
			<s:customData xmlns="http://www.wps.cn/officeDocument/2013/wpsCustomData"
				xmlns:s="http://www.wps.cn/officeDocument/2013/wpsCustomData">
				<customSectProps>
					<customSectPr />
				</customSectProps>
			</s:customData>
		</pkg:xmlData>
	</pkg:part>
	<pkg:part pkg:name="/customXml/itemProps1.xml"
		pkg:contentType="application/vnd.openxmlformats-officedocument.customXmlProperties+xml">
		<pkg:xmlData>
			<ds:datastoreItem ds:itemID="{B1977F7D-205B-4081-913C-38D41E755F92}"
				xmlns:ds="http://schemas.openxmlformats.org/officeDocument/2006/customXml">
				<ds:schemaRefs>
					<ds:schemaRef ds:uri="http://www.wps.cn/officeDocument/2013/wpsCustomData" />
				</ds:schemaRefs>
			</ds:datastoreItem>
		</pkg:xmlData>
	</pkg:part>
	<pkg:part pkg:name="/docProps/app.xml"
		pkg:contentType="application/vnd.openxmlformats-officedocument.extended-properties+xml">
		<pkg:xmlData>
			<Properties
				xmlns="http://schemas.openxmlformats.org/officeDocument/2006/extended-properties"
				xmlns:vt="http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes">
				<Pages>0</Pages>
				<Words>0</Words>
				<Characters>0</Characters>
				<Lines>0</Lines>
				<Paragraphs>0</Paragraphs>
				<ScaleCrop>false</ScaleCrop>
				<LinksUpToDate>false</LinksUpToDate>
				<CharactersWithSpaces>0</CharactersWithSpaces>
				<Application>WPS
					Office_10.1.0.6690_F1E327BC-269C-435d-A152-05C5408002CA
				</Application>
				<DocSecurity>0</DocSecurity>
			</Properties>
		</pkg:xmlData>
	</pkg:part>
	<pkg:part pkg:name="/docProps/core.xml"
		pkg:contentType="application/vnd.openxmlformats-package.core-properties+xml">
		<pkg:xmlData>
			<cp:coreProperties
				xmlns:cp="http://schemas.openxmlformats.org/package/2006/metadata/core-properties"
				xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:dcterms="http://purl.org/dc/terms/"
				xmlns:dcmitype="http://purl.org/dc/dcmitype/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
				<dcterms:created xsi:type="dcterms:W3CDTF">2017-05-22T08:36:00Z
				</dcterms:created>
				<dc:creator>Apache POI</dc:creator>
				<cp:lastModifiedBy>Administrator</cp:lastModifiedBy>
				<dcterms:modified xsi:type="dcterms:W3CDTF">2018-01-11T09:51:07Z
				</dcterms:modified>
			</cp:coreProperties>
		</pkg:xmlData>
	</pkg:part>
	<pkg:part pkg:name="/docProps/custom.xml"
		pkg:contentType="application/vnd.openxmlformats-officedocument.custom-properties+xml">
		<pkg:xmlData>
			<Properties
				xmlns="http://schemas.openxmlformats.org/officeDocument/2006/custom-properties"
				xmlns:vt="http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes">
				<property fmtid="{D5CDD505-2E9C-101B-9397-08002B2CF9AE}"
					pid="2" name="KSOProductBuildVer">
					<vt:lpwstr>2052-10.1.0.6690</vt:lpwstr>
				</property>
			</Properties>
		</pkg:xmlData>
	</pkg:part>
	<pkg:part pkg:name="/word/fontTable.xml"
		pkg:contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.fontTable+xml">
		<pkg:xmlData>
			<w:fonts
				xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
				xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
				xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
				xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
				mc:Ignorable="w14">
				<w:font w:name="Times New Roman">
					<w:panose1 w:val="02020603050405020304" />
					<w:charset w:val="00" />
					<w:family w:val="roman" />
					<w:pitch w:val="variable" />
					<w:sig w:usb0="20007A87" w:usb1="80000000" w:usb2="00000008"
						w:usb3="00000000" w:csb0="000001FF" w:csb1="00000000" />
				</w:font>
				<w:font w:name="宋体">
					<w:panose1 w:val="02010600030101010101" />
					<w:charset w:val="86" />
					<w:family w:val="auto" />
					<w:pitch w:val="default" />
					<w:sig w:usb0="00000003" w:usb1="288F0000" w:usb2="00000006"
						w:usb3="00000000" w:csb0="00040001" w:csb1="00000000" />
				</w:font>
				<w:font w:name="Wingdings">
					<w:panose1 w:val="05000000000000000000" />
					<w:charset w:val="02" />
					<w:family w:val="auto" />
					<w:pitch w:val="default" />
					<w:sig w:usb0="00000000" w:usb1="00000000" w:usb2="00000000"
						w:usb3="00000000" w:csb0="80000000" w:csb1="00000000" />
				</w:font>
				<w:font w:name="Arial">
					<w:panose1 w:val="020B0604020202020204" />
					<w:charset w:val="01" />
					<w:family w:val="swiss" />
					<w:pitch w:val="default" />
					<w:sig w:usb0="E0002EFF" w:usb1="C0007843" w:usb2="00000009"
						w:usb3="00000000" w:csb0="400001FF" w:csb1="FFFF0000" />
				</w:font>
				<w:font w:name="黑体">
					<w:panose1 w:val="02010609060101010101" />
					<w:charset w:val="86" />
					<w:family w:val="auto" />
					<w:pitch w:val="default" />
					<w:sig w:usb0="800002BF" w:usb1="38CF7CFA" w:usb2="00000016"
						w:usb3="00000000" w:csb0="00040001" w:csb1="00000000" />
				</w:font>
				<w:font w:name="Courier New">
					<w:panose1 w:val="02070309020205020404" />
					<w:charset w:val="01" />
					<w:family w:val="modern" />
					<w:pitch w:val="default" />
					<w:sig w:usb0="E0002EFF" w:usb1="C0007843" w:usb2="00000009"
						w:usb3="00000000" w:csb0="400001FF" w:csb1="FFFF0000" />
				</w:font>
				<w:font w:name="Symbol">
					<w:panose1 w:val="05050102010706020507" />
					<w:charset w:val="02" />
					<w:family w:val="roman" />
					<w:pitch w:val="default" />
					<w:sig w:usb0="00000000" w:usb1="00000000" w:usb2="00000000"
						w:usb3="00000000" w:csb0="80000000" w:csb1="00000000" />
				</w:font>
				<w:font w:name="Cambria">
					<w:panose1 w:val="02040503050406030204" />
					<w:charset w:val="00" />
					<w:family w:val="roman" />
					<w:pitch w:val="default" />
					<w:sig w:usb0="E00002FF" w:usb1="400004FF" w:usb2="00000000"
						w:usb3="00000000" w:csb0="2000019F" w:csb1="00000000" />
				</w:font>
				<w:font w:name="Calibri">
					<w:panose1 w:val="020F0502020204030204" />
					<w:charset w:val="00" />
					<w:family w:val="swiss" />
					<w:pitch w:val="default" />
					<w:sig w:usb0="E0002AFF" w:usb1="C000247B" w:usb2="00000009"
						w:usb3="00000000" w:csb0="200001FF" w:csb1="00000000" />
				</w:font>
				<w:font w:name="Open Sans">
					<w:altName w:val="Segoe Print" />
					<w:panose1 w:val="00000000000000000000" />
					<w:charset w:val="00" />
					<w:family w:val="auto" />
					<w:pitch w:val="default" />
					<w:sig w:usb0="00000000" w:usb1="00000000" w:usb2="00000000"
						w:usb3="00000000" w:csb0="00000000" w:csb1="00000000" />
				</w:font>
				<w:font w:name="Segoe Print">
					<w:panose1 w:val="02000600000000000000" />
					<w:charset w:val="00" />
					<w:family w:val="auto" />
					<w:pitch w:val="default" />
					<w:sig w:usb0="0000028F" w:usb1="00000000" w:usb2="00000000"
						w:usb3="00000000" w:csb0="2000009F" w:csb1="47010000" />
				</w:font>
				<w:font w:name="Consolas">
					<w:panose1 w:val="020B0609020204030204" />
					<w:charset w:val="86" />
					<w:family w:val="auto" />
					<w:pitch w:val="default" />
					<w:sig w:usb0="E00006FF" w:usb1="0000FCFF" w:usb2="00000001"
						w:usb3="00000000" w:csb0="6000019F" w:csb1="DFD70000" />
				</w:font>
			</w:fonts>
		</pkg:xmlData>
	</pkg:part>
	<pkg:part pkg:name="/word/settings.xml"
		pkg:contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.settings+xml">
		<pkg:xmlData>
			<w:settings
				xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
				xmlns:o="urn:schemas-microsoft-com:office:office"
				xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
				xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math"
				xmlns:v="urn:schemas-microsoft-com:vml" xmlns:w10="urn:schemas-microsoft-com:office:word"
				xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
				xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
				xmlns:sl="http://schemas.openxmlformats.org/schemaLibrary/2006/main"
				mc:Ignorable="w14">
				<w:zoom w:percent="210" />
				<w:bordersDoNotSurroundHeader w:val="0" />
				<w:bordersDoNotSurroundFooter w:val="0" />
				<w:documentProtection w:enforcement="0" />
				<w:displayHorizontalDrawingGridEvery
					w:val="1" />
				<w:displayVerticalDrawingGridEvery
					w:val="1" />
				<w:noPunctuationKerning w:val="1" />
				<w:compat>
					<w:doNotExpandShiftReturn />
					<w:doNotWrapTextWithPunct />
					<w:doNotUseEastAsianBreakRules />
					<w:useFELayout />
					<w:doNotUseIndentAsNumberingTabStop />
					<w:useAltKinsokuLineBreakRules />
					<w:compatSetting w:name="compatibilityMode"
						w:uri="http://schemas.microsoft.com/office/word" w:val="12" />
				</w:compat>
				<w:rsids>
					<w:rsidRoot w:val="00172A27" />
					<w:rsid w:val="053420B0" />
					<w:rsid w:val="08A852C5" />
					<w:rsid w:val="0EF17F9D" />
					<w:rsid w:val="15646B28" />
					<w:rsid w:val="1ECE0B1F" />
					<w:rsid w:val="26650BC8" />
					<w:rsid w:val="28B200BE" />
					<w:rsid w:val="2AC70B58" />
					<w:rsid w:val="392455A6" />
					<w:rsid w:val="3BD2299B" />
					<w:rsid w:val="3F7A720C" />
					<w:rsid w:val="42EF03FD" />
					<w:rsid w:val="4F736E88" />
					<w:rsid w:val="5E1F1921" />
					<w:rsid w:val="734F3DB9" />
					<w:rsid w:val="7450710E" />
					<w:rsid w:val="7F3E7E6B" />
					<w:rsid w:val="7FDD3ED7" />
				</w:rsids>
				<m:mathPr>
					<m:brkBin m:val="before" />
					<m:brkBinSub m:val="--" />
					<m:smallFrac m:val="0" />
					<m:dispDef />
					<m:lMargin m:val="0" />
					<m:rMargin m:val="0" />
					<m:defJc m:val="centerGroup" />
					<m:wrapIndent m:val="1440" />
					<m:intLim m:val="subSup" />
					<m:naryLim m:val="undOvr" />
				</m:mathPr>
				<w:themeFontLang w:val="en-US" w:eastAsia="zh-CN" />
				<w:clrSchemeMapping w:bg1="light1" w:t1="dark1"
					w:bg2="light2" w:t2="dark2" w:accent1="accent1" w:accent2="accent2"
					w:accent3="accent3" w:accent4="accent4" w:accent5="accent5"
					w:accent6="accent6" w:hyperlink="hyperlink" w:followedHyperlink="followedHyperlink" />
			</w:settings>
		</pkg:xmlData>
	</pkg:part>
	<pkg:part pkg:name="/word/styles.xml"
		pkg:contentType="application/vnd.openxmlformats-officedocument.wordprocessingml.styles+xml">
		<pkg:xmlData>
			<w:styles
				xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006"
				xmlns:o="urn:schemas-microsoft-com:office:office"
				xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
				xmlns:m="http://schemas.openxmlformats.org/officeDocument/2006/math"
				xmlns:v="urn:schemas-microsoft-com:vml"
				xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
				xmlns:w14="http://schemas.microsoft.com/office/word/2010/wordml"
				xmlns:w10="urn:schemas-microsoft-com:office:word"
				xmlns:sl="http://schemas.openxmlformats.org/schemaLibrary/2006/main"
				mc:Ignorable="w14">
				<w:docDefaults>
					<w:rPrDefault>
						<w:rPr>
							<w:rFonts w:ascii="Times New Roman" w:hAnsi="Times New Roman"
								w:eastAsia="宋体" w:cs="Times New Roman" />
						</w:rPr>
					</w:rPrDefault>
				</w:docDefaults>
				<w:latentStyles w:count="260" w:defQFormat="0"
					w:defUnhideWhenUsed="1" w:defSemiHidden="1" w:defUIPriority="99"
					w:defLockedState="0">
					<w:lsdException w:qFormat="1" w:unhideWhenUsed="0"
						w:uiPriority="0" w:semiHidden="0" w:name="Normal" />
					<w:lsdException w:qFormat="1" w:unhideWhenUsed="0"
						w:uiPriority="0" w:semiHidden="0" w:name="heading 1" />
					<w:lsdException w:qFormat="1" w:uiPriority="0"
						w:name="heading 2" />
					<w:lsdException w:qFormat="1" w:uiPriority="0"
						w:name="heading 3" />
					<w:lsdException w:qFormat="1" w:uiPriority="0"
						w:name="heading 4" />
					<w:lsdException w:qFormat="1" w:uiPriority="0"
						w:name="heading 5" />
					<w:lsdException w:qFormat="1" w:uiPriority="0"
						w:name="heading 6" />
					<w:lsdException w:qFormat="1" w:uiPriority="0"
						w:name="heading 7" />
					<w:lsdException w:qFormat="1" w:uiPriority="0"
						w:name="heading 8" />
					<w:lsdException w:qFormat="1" w:uiPriority="0"
						w:name="heading 9" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="index 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="index 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="index 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="index 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="index 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="index 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="index 7" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="index 8" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="index 9" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="toc 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="toc 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="toc 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="toc 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="toc 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="toc 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="toc 7" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="toc 8" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="toc 9" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Normal Indent" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="footnote text" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="annotation text" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="header" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="footer" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="index heading" />
					<w:lsdException w:qFormat="1" w:uiPriority="0"
						w:name="caption" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="table of figures" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="envelope address" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="envelope return" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="footnote reference" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="annotation reference" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="line number" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="page number" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="endnote reference" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="endnote text" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="table of authorities" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="macro" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="toa heading" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Bullet" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Number" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Bullet 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Bullet 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Bullet 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Bullet 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Number 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Number 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Number 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Number 5" />
					<w:lsdException w:qFormat="1" w:unhideWhenUsed="0"
						w:uiPriority="0" w:semiHidden="0" w:name="Title" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Closing" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Signature" />
					<w:lsdException w:qFormat="1" w:unhideWhenUsed="0"
						w:uiPriority="0" w:name="Default Paragraph Font" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Body Text" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Body Text Indent" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Continue" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Continue 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Continue 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Continue 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="List Continue 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Message Header" />
					<w:lsdException w:qFormat="1" w:unhideWhenUsed="0"
						w:uiPriority="0" w:semiHidden="0" w:name="Subtitle" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Salutation" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Date" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Body Text First Indent" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Body Text First Indent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Note Heading" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Body Text 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Body Text 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Body Text Indent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Body Text Indent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Block Text" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Hyperlink" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="FollowedHyperlink" />
					<w:lsdException w:qFormat="1" w:unhideWhenUsed="0"
						w:uiPriority="0" w:semiHidden="0" w:name="Strong" />
					<w:lsdException w:qFormat="1" w:unhideWhenUsed="0"
						w:uiPriority="0" w:semiHidden="0" w:name="Emphasis" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Document Map" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Plain Text" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="E-mail Signature" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Normal (Web)" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="HTML Acronym" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="HTML Address" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="HTML Cite" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="HTML Code" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="HTML Definition" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="HTML Keyboard" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="HTML Preformatted" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="HTML Sample" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="HTML Typewriter" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="HTML Variable" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:name="Normal Table" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="annotation subject" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Simple 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Simple 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Simple 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Classic 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Classic 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Classic 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Classic 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Colorful 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Colorful 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Colorful 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Columns 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Columns 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Columns 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Columns 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Columns 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Grid 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Grid 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Grid 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Grid 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Grid 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Grid 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Grid 7" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Grid 8" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table List 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table List 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table List 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table List 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table List 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table List 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table List 7" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table List 8" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table 3D effects 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table 3D effects 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table 3D effects 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Contemporary" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Elegant" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Professional" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Subtle 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Subtle 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Web 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Web 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Web 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Balloon Text" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Grid" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="0"
						w:semiHidden="0" w:name="Table Theme" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="60"
						w:semiHidden="0" w:name="Light Shading" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="61"
						w:semiHidden="0" w:name="Light List" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="62"
						w:semiHidden="0" w:name="Light Grid" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="63"
						w:semiHidden="0" w:name="Medium Shading 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="64"
						w:semiHidden="0" w:name="Medium Shading 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="65"
						w:semiHidden="0" w:name="Medium List 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="66"
						w:semiHidden="0" w:name="Medium List 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="67"
						w:semiHidden="0" w:name="Medium Grid 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="68"
						w:semiHidden="0" w:name="Medium Grid 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="69"
						w:semiHidden="0" w:name="Medium Grid 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="70"
						w:semiHidden="0" w:name="Dark List" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="71"
						w:semiHidden="0" w:name="Colorful Shading" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="72"
						w:semiHidden="0" w:name="Colorful List" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="73"
						w:semiHidden="0" w:name="Colorful Grid" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="60"
						w:semiHidden="0" w:name="Light Shading Accent 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="61"
						w:semiHidden="0" w:name="Light List Accent 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="62"
						w:semiHidden="0" w:name="Light Grid Accent 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="63"
						w:semiHidden="0" w:name="Medium Shading 1 Accent 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="64"
						w:semiHidden="0" w:name="Medium Shading 2 Accent 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="65"
						w:semiHidden="0" w:name="Medium List 1 Accent 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="66"
						w:semiHidden="0" w:name="Medium List 2 Accent 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="67"
						w:semiHidden="0" w:name="Medium Grid 1 Accent 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="68"
						w:semiHidden="0" w:name="Medium Grid 2 Accent 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="69"
						w:semiHidden="0" w:name="Medium Grid 3 Accent 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="70"
						w:semiHidden="0" w:name="Dark List Accent 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="71"
						w:semiHidden="0" w:name="Colorful Shading Accent 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="72"
						w:semiHidden="0" w:name="Colorful List Accent 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="73"
						w:semiHidden="0" w:name="Colorful Grid Accent 1" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="60"
						w:semiHidden="0" w:name="Light Shading Accent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="61"
						w:semiHidden="0" w:name="Light List Accent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="62"
						w:semiHidden="0" w:name="Light Grid Accent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="63"
						w:semiHidden="0" w:name="Medium Shading 1 Accent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="64"
						w:semiHidden="0" w:name="Medium Shading 2 Accent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="65"
						w:semiHidden="0" w:name="Medium List 1 Accent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="66"
						w:semiHidden="0" w:name="Medium List 2 Accent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="67"
						w:semiHidden="0" w:name="Medium Grid 1 Accent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="68"
						w:semiHidden="0" w:name="Medium Grid 2 Accent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="69"
						w:semiHidden="0" w:name="Medium Grid 3 Accent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="70"
						w:semiHidden="0" w:name="Dark List Accent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="71"
						w:semiHidden="0" w:name="Colorful Shading Accent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="72"
						w:semiHidden="0" w:name="Colorful List Accent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="73"
						w:semiHidden="0" w:name="Colorful Grid Accent 2" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="60"
						w:semiHidden="0" w:name="Light Shading Accent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="61"
						w:semiHidden="0" w:name="Light List Accent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="62"
						w:semiHidden="0" w:name="Light Grid Accent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="63"
						w:semiHidden="0" w:name="Medium Shading 1 Accent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="64"
						w:semiHidden="0" w:name="Medium Shading 2 Accent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="65"
						w:semiHidden="0" w:name="Medium List 1 Accent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="66"
						w:semiHidden="0" w:name="Medium List 2 Accent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="67"
						w:semiHidden="0" w:name="Medium Grid 1 Accent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="68"
						w:semiHidden="0" w:name="Medium Grid 2 Accent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="69"
						w:semiHidden="0" w:name="Medium Grid 3 Accent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="70"
						w:semiHidden="0" w:name="Dark List Accent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="71"
						w:semiHidden="0" w:name="Colorful Shading Accent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="72"
						w:semiHidden="0" w:name="Colorful List Accent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="73"
						w:semiHidden="0" w:name="Colorful Grid Accent 3" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="60"
						w:semiHidden="0" w:name="Light Shading Accent 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="61"
						w:semiHidden="0" w:name="Light List Accent 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="62"
						w:semiHidden="0" w:name="Light Grid Accent 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="63"
						w:semiHidden="0" w:name="Medium Shading 1 Accent 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="64"
						w:semiHidden="0" w:name="Medium Shading 2 Accent 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="65"
						w:semiHidden="0" w:name="Medium List 1 Accent 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="66"
						w:semiHidden="0" w:name="Medium List 2 Accent 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="67"
						w:semiHidden="0" w:name="Medium Grid 1 Accent 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="68"
						w:semiHidden="0" w:name="Medium Grid 2 Accent 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="69"
						w:semiHidden="0" w:name="Medium Grid 3 Accent 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="70"
						w:semiHidden="0" w:name="Dark List Accent 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="71"
						w:semiHidden="0" w:name="Colorful Shading Accent 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="72"
						w:semiHidden="0" w:name="Colorful List Accent 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="73"
						w:semiHidden="0" w:name="Colorful Grid Accent 4" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="60"
						w:semiHidden="0" w:name="Light Shading Accent 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="61"
						w:semiHidden="0" w:name="Light List Accent 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="62"
						w:semiHidden="0" w:name="Light Grid Accent 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="63"
						w:semiHidden="0" w:name="Medium Shading 1 Accent 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="64"
						w:semiHidden="0" w:name="Medium Shading 2 Accent 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="65"
						w:semiHidden="0" w:name="Medium List 1 Accent 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="66"
						w:semiHidden="0" w:name="Medium List 2 Accent 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="67"
						w:semiHidden="0" w:name="Medium Grid 1 Accent 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="68"
						w:semiHidden="0" w:name="Medium Grid 2 Accent 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="69"
						w:semiHidden="0" w:name="Medium Grid 3 Accent 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="70"
						w:semiHidden="0" w:name="Dark List Accent 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="71"
						w:semiHidden="0" w:name="Colorful Shading Accent 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="72"
						w:semiHidden="0" w:name="Colorful List Accent 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="73"
						w:semiHidden="0" w:name="Colorful Grid Accent 5" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="60"
						w:semiHidden="0" w:name="Light Shading Accent 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="61"
						w:semiHidden="0" w:name="Light List Accent 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="62"
						w:semiHidden="0" w:name="Light Grid Accent 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="63"
						w:semiHidden="0" w:name="Medium Shading 1 Accent 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="64"
						w:semiHidden="0" w:name="Medium Shading 2 Accent 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="65"
						w:semiHidden="0" w:name="Medium List 1 Accent 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="66"
						w:semiHidden="0" w:name="Medium List 2 Accent 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="67"
						w:semiHidden="0" w:name="Medium Grid 1 Accent 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="68"
						w:semiHidden="0" w:name="Medium Grid 2 Accent 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="69"
						w:semiHidden="0" w:name="Medium Grid 3 Accent 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="70"
						w:semiHidden="0" w:name="Dark List Accent 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="71"
						w:semiHidden="0" w:name="Colorful Shading Accent 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="72"
						w:semiHidden="0" w:name="Colorful List Accent 6" />
					<w:lsdException w:unhideWhenUsed="0" w:uiPriority="73"
						w:semiHidden="0" w:name="Colorful Grid Accent 6" />
				</w:latentStyles>
				<w:style w:type="paragraph" w:default="1" w:styleId="1">
					<w:name w:val="Normal" />
					<w:qFormat />
					<w:uiPriority w:val="0" />
					<w:pPr>
						<w:widowControl w:val="0" />
						<w:jc w:val="both" />
					</w:pPr>
					<w:rPr>
						<w:rFonts w:ascii="Times New Roman" w:hAnsi="Times New Roman"
							w:eastAsia="宋体" w:cs="Times New Roman" />
						<w:sz w:val="21" />
						<w:szCs w:val="22" />
					</w:rPr>
				</w:style>
				<w:style w:type="paragraph" w:styleId="2">
					<w:name w:val="heading 1" />
					<w:basedOn w:val="1" />
					<w:next w:val="1" />
					<w:qFormat />
					<w:uiPriority w:val="0" />
					<w:pPr>
						<w:keepNext />
						<w:keepLines />
						<w:spacing w:before="340" w:beforeLines="0"
							w:beforeAutospacing="0" w:after="330" w:afterLines="0"
							w:afterAutospacing="0" w:line="576" w:lineRule="auto" />
						<w:outlineLvl w:val="0" />
					</w:pPr>
					<w:rPr>
						<w:b />
						<w:kern w:val="44" />
						<w:sz w:val="44" />
					</w:rPr>
				</w:style>
				<w:style w:type="character" w:default="1" w:styleId="4">
					<w:name w:val="Default Paragraph Font" />
					<w:semiHidden />
					<w:qFormat />
					<w:uiPriority w:val="0" />
				</w:style>
				<w:style w:type="table" w:default="1" w:styleId="5">
					<w:name w:val="Normal Table" />
					<w:semiHidden />
					<w:uiPriority w:val="0" />
					<w:tblPr>
						<w:tblLayout w:type="fixed" />
						<w:tblCellMar>
							<w:top w:w="0" w:type="dxa" />
							<w:left w:w="108" w:type="dxa" />
							<w:bottom w:w="0" w:type="dxa" />
							<w:right w:w="108" w:type="dxa" />
						</w:tblCellMar>
					</w:tblPr>
				</w:style>
				<w:style w:type="paragraph" w:styleId="3">
					<w:name w:val="toc 1" />
					<w:basedOn w:val="1" />
					<w:next w:val="1" />
					<w:uiPriority w:val="0" />
				</w:style>
			</w:styles>
		</pkg:xmlData>
	</pkg:part>
	<pkg:part pkg:name="/word/theme/theme1.xml"
		pkg:contentType="application/vnd.openxmlformats-officedocument.theme+xml">
		<pkg:xmlData>
			<a:theme xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main"
				name="Office">
				<a:themeElements>
					<a:clrScheme name="Office">
						<a:dk1>
							<a:sysClr val="windowText" lastClr="000000" />
						</a:dk1>
						<a:lt1>
							<a:sysClr val="window" lastClr="FFFFFF" />
						</a:lt1>
						<a:dk2>
							<a:srgbClr val="1F497D" />
						</a:dk2>
						<a:lt2>
							<a:srgbClr val="EEECE1" />
						</a:lt2>
						<a:accent1>
							<a:srgbClr val="4F81BD" />
						</a:accent1>
						<a:accent2>
							<a:srgbClr val="C0504D" />
						</a:accent2>
						<a:accent3>
							<a:srgbClr val="9BBB59" />
						</a:accent3>
						<a:accent4>
							<a:srgbClr val="8064A2" />
						</a:accent4>
						<a:accent5>
							<a:srgbClr val="4BACC6" />
						</a:accent5>
						<a:accent6>
							<a:srgbClr val="F79646" />
						</a:accent6>
						<a:hlink>
							<a:srgbClr val="0000FF" />
						</a:hlink>
						<a:folHlink>
							<a:srgbClr val="800080" />
						</a:folHlink>
					</a:clrScheme>
					<a:fontScheme name="Office">
						<a:majorFont>
							<a:latin typeface="Cambria" />
							<a:ea typeface="" />
							<a:cs typeface="" />
							<a:font script="Jpan" typeface="ＭＳ ゴシック" />
							<a:font script="Hang" typeface="맑은 고딕" />
							<a:font script="Hans" typeface="宋体" />
							<a:font script="Hant" typeface="新細明體" />
							<a:font script="Arab" typeface="Times New Roman" />
							<a:font script="Hebr" typeface="Times New Roman" />
							<a:font script="Thai" typeface="Angsana New" />
							<a:font script="Ethi" typeface="Nyala" />
							<a:font script="Beng" typeface="Vrinda" />
							<a:font script="Gujr" typeface="Shruti" />
							<a:font script="Khmr" typeface="MoolBoran" />
							<a:font script="Knda" typeface="Tunga" />
							<a:font script="Guru" typeface="Raavi" />
							<a:font script="Cans" typeface="Euphemia" />
							<a:font script="Cher" typeface="Plantagenet Cherokee" />
							<a:font script="Yiii" typeface="Microsoft Yi Baiti" />
							<a:font script="Tibt" typeface="Microsoft Himalaya" />
							<a:font script="Thaa" typeface="MV Boli" />
							<a:font script="Deva" typeface="Mangal" />
							<a:font script="Telu" typeface="Gautami" />
							<a:font script="Taml" typeface="Latha" />
							<a:font script="Syrc" typeface="Estrangelo Edessa" />
							<a:font script="Orya" typeface="Kalinga" />
							<a:font script="Mlym" typeface="Kartika" />
							<a:font script="Laoo" typeface="DokChampa" />
							<a:font script="Sinh" typeface="Iskoola Pota" />
							<a:font script="Mong" typeface="Mongolian Baiti" />
							<a:font script="Viet" typeface="Times New Roman" />
							<a:font script="Uigh" typeface="Microsoft Uighur" />
							<a:font script="Geor" typeface="Sylfaen" />
						</a:majorFont>
						<a:minorFont>
							<a:latin typeface="Calibri" />
							<a:ea typeface="" />
							<a:cs typeface="" />
							<a:font script="Jpan" typeface="ＭＳ 明朝" />
							<a:font script="Hang" typeface="맑은 고딕" />
							<a:font script="Hans" typeface="宋体" />
							<a:font script="Hant" typeface="新細明體" />
							<a:font script="Arab" typeface="Arial" />
							<a:font script="Hebr" typeface="Arial" />
							<a:font script="Thai" typeface="Cordia New" />
							<a:font script="Ethi" typeface="Nyala" />
							<a:font script="Beng" typeface="Vrinda" />
							<a:font script="Gujr" typeface="Shruti" />
							<a:font script="Khmr" typeface="DaunPenh" />
							<a:font script="Knda" typeface="Tunga" />
							<a:font script="Guru" typeface="Raavi" />
							<a:font script="Cans" typeface="Euphemia" />
							<a:font script="Cher" typeface="Plantagenet Cherokee" />
							<a:font script="Yiii" typeface="Microsoft Yi Baiti" />
							<a:font script="Tibt" typeface="Microsoft Himalaya" />
							<a:font script="Thaa" typeface="MV Boli" />
							<a:font script="Deva" typeface="Mangal" />
							<a:font script="Telu" typeface="Gautami" />
							<a:font script="Taml" typeface="Latha" />
							<a:font script="Syrc" typeface="Estrangelo Edessa" />
							<a:font script="Orya" typeface="Kalinga" />
							<a:font script="Mlym" typeface="Kartika" />
							<a:font script="Laoo" typeface="DokChampa" />
							<a:font script="Sinh" typeface="Iskoola Pota" />
							<a:font script="Mong" typeface="Mongolian Baiti" />
							<a:font script="Viet" typeface="Arial" />
							<a:font script="Uigh" typeface="Microsoft Uighur" />
							<a:font script="Geor" typeface="Sylfaen" />
						</a:minorFont>
					</a:fontScheme>
					<a:fmtScheme name="Office">
						<a:fillStyleLst>
							<a:solidFill>
								<a:schemeClr val="phClr" />
							</a:solidFill>
							<a:gradFill rotWithShape="1">
								<a:gsLst>
									<a:gs pos="0">
										<a:schemeClr val="phClr">
											<a:tint val="50000" />
											<a:satMod val="300000" />
										</a:schemeClr>
									</a:gs>
									<a:gs pos="35000">
										<a:schemeClr val="phClr">
											<a:tint val="37000" />
											<a:satMod val="300000" />
										</a:schemeClr>
									</a:gs>
									<a:gs pos="100000">
										<a:schemeClr val="phClr">
											<a:tint val="15000" />
											<a:satMod val="350000" />
										</a:schemeClr>
									</a:gs>
								</a:gsLst>
								<a:lin ang="16200000" scaled="1" />
							</a:gradFill>
							<a:gradFill rotWithShape="1">
								<a:gsLst>
									<a:gs pos="0">
										<a:schemeClr val="phClr">
											<a:shade val="51000" />
											<a:satMod val="130000" />
										</a:schemeClr>
									</a:gs>
									<a:gs pos="80000">
										<a:schemeClr val="phClr">
											<a:shade val="93000" />
											<a:satMod val="130000" />
										</a:schemeClr>
									</a:gs>
									<a:gs pos="100000">
										<a:schemeClr val="phClr">
											<a:shade val="94000" />
											<a:satMod val="135000" />
										</a:schemeClr>
									</a:gs>
								</a:gsLst>
								<a:lin ang="16200000" scaled="0" />
							</a:gradFill>
						</a:fillStyleLst>
						<a:lnStyleLst>
							<a:ln w="9525" cap="flat" cmpd="sng" algn="ctr">
								<a:solidFill>
									<a:schemeClr val="phClr">
										<a:shade val="95000" />
										<a:satMod val="105000" />
									</a:schemeClr>
								</a:solidFill>
								<a:prstDash val="solid" />
							</a:ln>
							<a:ln w="25400" cap="flat" cmpd="sng" algn="ctr">
								<a:solidFill>
									<a:schemeClr val="phClr" />
								</a:solidFill>
								<a:prstDash val="solid" />
							</a:ln>
							<a:ln w="38100" cap="flat" cmpd="sng" algn="ctr">
								<a:solidFill>
									<a:schemeClr val="phClr" />
								</a:solidFill>
								<a:prstDash val="solid" />
							</a:ln>
						</a:lnStyleLst>
						<a:effectStyleLst>
							<a:effectStyle>
								<a:effectLst>
									<a:outerShdw blurRad="40000" dist="20000" dir="5400000"
										rotWithShape="0">
										<a:srgbClr val="000000">
											<a:alpha val="38000" />
										</a:srgbClr>
									</a:outerShdw>
								</a:effectLst>
							</a:effectStyle>
							<a:effectStyle>
								<a:effectLst>
									<a:outerShdw blurRad="40000" dist="23000" dir="5400000"
										rotWithShape="0">
										<a:srgbClr val="000000">
											<a:alpha val="35000" />
										</a:srgbClr>
									</a:outerShdw>
								</a:effectLst>
							</a:effectStyle>
							<a:effectStyle>
								<a:effectLst>
									<a:outerShdw blurRad="40000" dist="23000" dir="5400000"
										rotWithShape="0">
										<a:srgbClr val="000000">
											<a:alpha val="35000" />
										</a:srgbClr>
									</a:outerShdw>
								</a:effectLst>
								<a:scene3d>
									<a:camera prst="orthographicFront">
										<a:rot lat="0" lon="0" rev="0" />
									</a:camera>
									<a:lightRig rig="threePt" dir="t">
										<a:rot lat="0" lon="0" rev="1200000" />
									</a:lightRig>
								</a:scene3d>
								<a:sp3d>
									<a:bevelT w="63500" h="25400" />
								</a:sp3d>
							</a:effectStyle>
						</a:effectStyleLst>
						<a:bgFillStyleLst>
							<a:solidFill>
								<a:schemeClr val="phClr" />
							</a:solidFill>
							<a:gradFill rotWithShape="1">
								<a:gsLst>
									<a:gs pos="0">
										<a:schemeClr val="phClr">
											<a:tint val="40000" />
											<a:satMod val="350000" />
										</a:schemeClr>
									</a:gs>
									<a:gs pos="40000">
										<a:schemeClr val="phClr">
											<a:tint val="45000" />
											<a:satMod val="350000" />
											<a:shade val="99000" />
										</a:schemeClr>
									</a:gs>
									<a:gs pos="100000">
										<a:schemeClr val="phClr">
											<a:shade val="20000" />
											<a:satMod val="255000" />
										</a:schemeClr>
									</a:gs>
								</a:gsLst>
								<a:path path="circle">
									<a:fillToRect l="50000" t="-80000" r="50000" b="180000" />
								</a:path>
							</a:gradFill>
							<a:gradFill rotWithShape="1">
								<a:gsLst>
									<a:gs pos="0">
										<a:schemeClr val="phClr">
											<a:tint val="80000" />
											<a:satMod val="300000" />
										</a:schemeClr>
									</a:gs>
									<a:gs pos="100000">
										<a:schemeClr val="phClr">
											<a:shade val="30000" />
											<a:satMod val="200000" />
										</a:schemeClr>
									</a:gs>
								</a:gsLst>
								<a:path path="circle">
									<a:fillToRect l="50000" t="50000" r="50000" b="50000" />
								</a:path>
							</a:gradFill>
						</a:bgFillStyleLst>
					</a:fmtScheme>
				</a:themeElements>
				<a:objectDefaults />
			</a:theme>
		</pkg:xmlData>
	</pkg:part>
</pkg:package>