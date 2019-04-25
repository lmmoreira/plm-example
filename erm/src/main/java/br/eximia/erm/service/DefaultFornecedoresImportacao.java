package br.eximia.erm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.eximia.erm.model.Fornecedor;
import br.eximia.erm.model.Usuario;
import br.eximia.erm.service.interfaces.UsuarioService;

@Component("defaultFornecedoresImportacao")
public class DefaultFornecedoresImportacao extends AbstractImportacao<Usuario, Long> {
	
	@Autowired
	UsuarioService defaultUsuarioService;
	
	@Override
	protected void parse() {
		try {
			List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
			HSSFWorkbook workbook = new HSSFWorkbook(this.getImportacao().getFileStream());
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = null;
					Fornecedor fornecedor = new Fornecedor();
					cell = cellIterator.next();
					fornecedor.setFornecedor(cell.getStringCellValue());
					cell = cellIterator.next();
					fornecedor.setCnpj(cell.getStringCellValue());
					cell = cellIterator.next();
					fornecedor.setContato(cell.getStringCellValue());
					cell = cellIterator.next();
					fornecedor.setEndereco(cell.getStringCellValue());
					cell = cellIterator.next();
					fornecedor.setTelefone(cell.getStringCellValue());
					cell = cellIterator.next();
					fornecedor.setEmail(cell.getStringCellValue());
					cell = cellIterator.next();
					fornecedor.setProduto(cell.getStringCellValue());
					cell = cellIterator.next();
					fornecedor.setIqf(cell.getNumericCellValue());
					cell = cellIterator.next();
					fornecedor.setIqpf(cell.getNumericCellValue());
					cell = cellIterator.next();
					fornecedor.setIde(cell.getNumericCellValue());
					cell = cellIterator.next();
					fornecedor.setCriador(defaultUsuarioService.findByUsuario(cell.getStringCellValue()));
					fornecedor.setDataCadastro(new Date());
					fornecedor.setDataReavaliacao(new Date());
					fornecedores.add(fornecedor);
				}
			}
			this.getImportacao().setItems(fornecedores);
			workbook.close();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected String getFileName() {
		return "fornecedores.xls";
	}

}
