package br.eximia.erm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.UnidadeLote;

@Component("defaultUnidadesLoteImportacao")
public class DefaultUnidadesLoteImportacao extends AbstractImportacao<UnidadeLote, Long> {

	@Override
	protected void parse() {
		try {
			List<UnidadeLote> unidadesLote = new ArrayList<UnidadeLote>();
			HSSFWorkbook workbook = new HSSFWorkbook(this.getImportacao().getFileStream());
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					UnidadeLote unidadeLote = new UnidadeLote();
					unidadeLote.setUnidade(cell.getStringCellValue());
					unidadesLote.add(unidadeLote);
				}
			}
			this.getImportacao().setItems(unidadesLote);
			workbook.close();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected String getFileName() {
		return "unidadeLote.xls";
	}

}
