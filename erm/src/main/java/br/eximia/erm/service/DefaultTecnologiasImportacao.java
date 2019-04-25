package br.eximia.erm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.Tecnologia;
import br.eximia.erm.service.interfaces.TecnologiaService;

@Component("defaultTecnologiasImportacao")
public class DefaultTecnologiasImportacao extends AbstractImportacao<Tecnologia, Long> {

	@Autowired
	TecnologiaService tecnologiaService;

	@Override
	protected void parse() {
		try {
			List<Tecnologia> tecnologias = new ArrayList<Tecnologia>();
			HSSFWorkbook workbook = new HSSFWorkbook(this.getImportacao().getFileStream());
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					Tecnologia tecnologia = new Tecnologia();
					tecnologia.setTecnologia(cell.getStringCellValue());
					tecnologias.add(tecnologia);
				}
			}
			this.getImportacao().setItems(tecnologias);
			workbook.close();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected String getFileName() {
		return "tecnologias.xls";
	}

}
