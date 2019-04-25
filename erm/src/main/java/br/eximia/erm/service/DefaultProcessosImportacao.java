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

import br.eximia.erm.model.ProcessoEspecial;
import br.eximia.erm.service.interfaces.ProcessoEspecialService;
import br.eximia.erm.service.interfaces.UsuarioService;

@Component("defaultProcessosImportacao")
public class DefaultProcessosImportacao extends AbstractImportacao<ProcessoEspecial, Long> {

	@Autowired
	ProcessoEspecialService defaultProcessoEspecialService;
	
	@Autowired
	UsuarioService defaultUsuarioService;

	@Override
	protected void parse() {
		try {
			List<ProcessoEspecial> processos = new ArrayList<ProcessoEspecial>();
			HSSFWorkbook workbook = new HSSFWorkbook(this.getImportacao().getFileStream());
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				ProcessoEspecial processo = new ProcessoEspecial();
				Cell cell = null;
				cell = cellIterator.next();
				processo.setProcesso(cell.getStringCellValue());
				cell = cellIterator.next();
				processo.setEspecificacao(cell.getStringCellValue());
				cell = cellIterator.next();
				processo.setTexto(cell.getStringCellValue());
				cell = cellIterator.next();
				processo.setCriador(defaultUsuarioService.findByUsuario(cell.getStringCellValue()));
				processos.add(processo);
			}
			this.getImportacao().setItems(processos);
			workbook.close();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected String getFileName() {
		return "processos.xls";
	}

}
