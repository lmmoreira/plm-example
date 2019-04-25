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

import br.eximia.erm.model.Regra;
import br.eximia.erm.model.Usuario;
import br.eximia.erm.service.interfaces.RegraService;

@Component("defaultUsuariosImportacao")
public class DefaultUsuariosImportacao extends AbstractImportacao<Usuario, Long> {

	@Autowired
	RegraService defaultRegraService;
	
	@Override
	protected void parse() {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			HSSFWorkbook workbook = new HSSFWorkbook(this.getImportacao().getFileStream());
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = null;
					Usuario usuario = new Usuario();
					cell = cellIterator.next();
					usuario.setUsuario(cell.getStringCellValue());
					cell = cellIterator.next();
					usuario.setNome(cell.getStringCellValue());
					cell = cellIterator.next();
					usuario.setEmail(cell.getStringCellValue());
					cell = cellIterator.next();
					usuario.setSenha(cell.getStringCellValue());
					usuario.setStatus(true);
					cell = cellIterator.next();
					String[] regras = cell.getStringCellValue().split(",");
					
					for(String regra : regras){
						Regra r = defaultRegraService.findByRegra(regra.trim());
						if(r != null)
							usuario.getRegras().add(r);
					}
					usuarios.add(usuario);
				}
			}
			this.getImportacao().setItems(usuarios);
			workbook.close();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected String getFileName() {
		return "usuarios.xls";
	}

}
