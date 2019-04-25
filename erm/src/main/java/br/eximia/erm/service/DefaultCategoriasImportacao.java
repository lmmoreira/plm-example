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

import br.eximia.erm.model.Categoria;
import br.eximia.erm.model.Tecnologia;
import br.eximia.erm.service.interfaces.CategoriaService;
import br.eximia.erm.service.interfaces.TecnologiaService;

@Component("defaultCategoriasImportacao")
public class DefaultCategoriasImportacao extends AbstractImportacao<Categoria, Long> {

	@Autowired
	CategoriaService defaultCategoriaService;
	
	@Autowired
	TecnologiaService defaultTecnologiaService;

	@Override
	protected void parse() {
		try {
			List<Categoria> categorias = new ArrayList<Categoria>();
			HSSFWorkbook workbook = new HSSFWorkbook(this.getImportacao().getFileStream());
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				Categoria categoria = new Categoria();
				Cell cell = null;
				
				cell = cellIterator.next();
				categoria.setCategoria(cell.getStringCellValue());
				cell = cellIterator.next();
				
				Tecnologia tecnologia = defaultTecnologiaService.findByTecnologia(cell.getStringCellValue());
				
				if(tecnologia == null){
					tecnologia = new Tecnologia();
					tecnologia.setTecnologia(cell.getStringCellValue());
				}
				
				categoria.setTecnologia(tecnologia);
				categorias.add(categoria);
			}
			this.getImportacao().setItems(categorias);
			workbook.close();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected String getFileName() {
		return "categorias.xls";
	}
	
	@Override
	protected void detalhes() {
		for(Object categoria : this.getImportacao().getItems()){
			Tecnologia tecnologia = ((Categoria) categoria).getTecnologia();
			
			try {
				defaultTecnologiaService.save(((Categoria) categoria).getTecnologia());
				defaultCategoriaService.save((Categoria) categoria);
				
				if(tecnologia.isNew()){
					this.getImportacao().getDetalhes().add(getNovoDetalhe(tecnologia));
				}
				
				this.getImportacao().getDetalhes().add(getNovoDetalhe(categoria));
			} catch (Exception e) {
				this.getImportacao().getDetalhes().add(this.getNovoErro(categoria, e));
			}
		}
	}
}
