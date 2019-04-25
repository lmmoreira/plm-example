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

import br.eximia.erm.model.Material;
import br.eximia.erm.model.MaterialAprovador;
import br.eximia.erm.model.MaterialInformacaoAlternativa;
import br.eximia.erm.model.ProcessoEspecial;
import br.eximia.erm.model.TipoMaterial;
import br.eximia.erm.model.TracoLado;
import br.eximia.erm.model.Usuario;
import br.eximia.erm.service.interfaces.AtaService;
import br.eximia.erm.service.interfaces.CategoriaService;
import br.eximia.erm.service.interfaces.FornecedorService;
import br.eximia.erm.service.interfaces.MaterialService;
import br.eximia.erm.service.interfaces.ProcessoEspecialService;
import br.eximia.erm.service.interfaces.ProgramaService;
import br.eximia.erm.service.interfaces.SubAtaService;
import br.eximia.erm.service.interfaces.TracoService;
import br.eximia.erm.service.interfaces.UsuarioService;

@Component("defaultMateriaisImportacao")
public class DefaultMateriaisImportacao extends AbstractImportacao<Usuario, Long> {

	@Autowired
	UsuarioService defaultUsuarioService;

	@Autowired
	CategoriaService defaultCategoriaService;

	@Autowired
	ProgramaService defaultProgramaService;

	@Autowired
	AtaService defaultAtaService;

	@Autowired
	SubAtaService defaultSubAtaService;

	@Autowired
	TracoService defaultTracoService;

	@Autowired
	FornecedorService defaultFornecedorService;

	@Autowired
	ProcessoEspecialService defaultProcessoEspecialService;

	@Autowired
	MaterialService defaultMaterialService;

	@Override
	protected void parse() {
		try {
			List<Material> materiais = new ArrayList<Material>();
			HSSFWorkbook workbook = new HSSFWorkbook(this.getImportacao().getFileStream());
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();

			String fieldError = "";
			int line = 1;

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Cell cell = null;

				try {
					cell = row.getCell(9, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.ncode");
					Material material = (cell != null)
							? defaultMaterialService.findByNcode((long) cell.getNumericCellValue()) : new Material();
					cell = row.getCell(0, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.tipo");
					material.setTipo(TipoMaterial.valueOf(cell.getStringCellValue().toUpperCase()));
					cell = row.getCell(1, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.categoria");
					material.setCategoria(defaultCategoriaService.findByCategoria(cell.getStringCellValue()));
					cell = row.getCell(2, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.pn");
					material.setPn(
							(cell != null)
									? ((cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
											? String.valueOf(cell.getNumericCellValue()) : cell.getStringCellValue())
									: null);
					cell = row.getCell(3, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.material");
					material.setMaterial((cell != null) ? cell.getStringCellValue() : null);
					cell = row.getCell(4, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.fornecedor");
					material.setFornecedor(defaultFornecedorService
							.findByFornecedor((cell != null) ? cell.getStringCellValue() : null));

					cell = row.getCell(5, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.processos");
					String[] processosEspeciais = (cell != null) ? cell.getStringCellValue().split(",") : new String[0];
					material.setProcessosEspeciais(new ArrayList<ProcessoEspecial>());
					for (String processo : processosEspeciais) {
						ProcessoEspecial p = defaultProcessoEspecialService.findByProcesso(processo.trim());
						if (p != null)
							material.getProcessosEspeciais().add(p);
					}

					cell = row.getCell(6, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.especificacao");
					material.setEspecificacao(
							(cell != null)
									? ((cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
											? String.valueOf(cell.getNumericCellValue()) : cell.getStringCellValue())
									: null);
					cell = row.getCell(7, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.pnFornecedor");
					material.setPnFornecedor(
							(cell != null)
									? ((cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
											? String.valueOf(cell.getNumericCellValue()) : cell.getStringCellValue())
									: null);
					cell = row.getCell(8, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.usuario");
					Usuario criador = defaultUsuarioService.findByUsuario(cell.getStringCellValue());

					if (criador == null)
						throw new Exception(super.getMessages().getMessage("importacacoMaterial.usuario.falta"));

					material.setCriador(criador);
					cell = row.getCell(10, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.submeterAprov");
					material.setPublicado((cell != null) ? (cell.getStringCellValue().equals("S")) : false);

					cell = row.getCell(11, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.processos");
					String[] aprovadores = (cell != null) ? cell.getStringCellValue().split(",") : new String[0];

					if ((material.getAprovadores() == null) || (material.getAprovadores().size() == 0)) {
						material.setAprovadores(new ArrayList<MaterialAprovador>());
						for (String aprovador : aprovadores) {
							Usuario a = defaultUsuarioService.findByUsuario(aprovador.trim());

							if (a != null) {
								MaterialAprovador ma = new MaterialAprovador(material, a);
								if (ma != null)
									material.getAprovadores().add(ma);
							}
						}
					}

					cell = row.getCell(12, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.descricaoPT");
					material.setDescricaoPT(
							(cell != null)
									? ((cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
											? String.valueOf(cell.getNumericCellValue()) : cell.getStringCellValue())
									: null);

					cell = row.getCell(13, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.qtdMat");
					material.setQtdeMat(
							(cell != null)
									? ((cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
											? String.valueOf(cell.getNumericCellValue()) : cell.getStringCellValue())
									: null);

					cell = row.getCell(14, Row.RETURN_BLANK_AS_NULL);
					fieldError = super.getMessages().getMessage("importacacoMaterial.ncm");
					material.setNcm(
							(cell != null)
									? ((cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
											? String.valueOf(cell.getNumericCellValue()) : cell.getStringCellValue())
									: null);

					cell = row.getCell(15, Row.RETURN_BLANK_AS_NULL);
					if (cell != null) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						fieldError = super.getMessages().getMessage("importacacoMaterial.ncodeAlternativo");
						String[] ncodes = (cell != null) ? cell.getStringCellValue().replaceAll("\\.", ",").split(",")
								: new String[0];
						if ((material.getInformacoesAlternativas() == null)
								|| (material.getInformacoesAlternativas().size() == 0)) {
							for (String ncode : ncodes) {
								Material ncodeAlternativo = defaultMaterialService
										.findByNcode(Long.parseLong(ncode.trim()));

								if (ncodeAlternativo == null)
									continue;

								MaterialInformacaoAlternativa ia = new MaterialInformacaoAlternativa();
								ia.setMaterial(material);
								ia.setNcodeAlternativo(ncodeAlternativo);
								material.getInformacoesAlternativas().add(ia);
							}
						}
					}

					cell = row.getCell(16, Row.RETURN_BLANK_AS_NULL);
					if (cell != null) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						fieldError = super.getMessages().getMessage("importacacoMaterial.programa");
						material.setPrograma(defaultProgramaService
								.findByPrograma(String.format("%03d", Integer.parseInt(cell.getStringCellValue()))));
					}

					cell = row.getCell(17, Row.RETURN_BLANK_AS_NULL);
					if (cell != null) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						fieldError = super.getMessages().getMessage("importacacoMaterial.ata");
						material.setAtaO(defaultAtaService
								.findByAta(String.format("%02d", Integer.parseInt(cell.getStringCellValue()))));
					}

					cell = row.getCell(18, Row.RETURN_BLANK_AS_NULL);
					if (cell != null) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						fieldError = super.getMessages().getMessage("importacacoMaterial.sub");
						material.setSubAtaO(defaultSubAtaService
								.findBySubAta(String.format("%02d", Integer.parseInt(cell.getStringCellValue()))));
					}

					cell = row.getCell(19, Row.RETURN_BLANK_AS_NULL);
					if (cell != null) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						fieldError = super.getMessages().getMessage("importacacoMaterial.traco");
						material.setTraco(defaultTracoService.findByTracoAndLado(
								Integer.parseInt(cell.getStringCellValue().split("-")[0]),
								TracoLado.valueOf(capitalizeFirst(cell.getStringCellValue().split("-")[1]))));
					}

					if(material.isNew()) {
						material.generateRadical();
						material.gerarPN();
					}

					materiais.add(material);
					line++;
				} catch (Exception e) {
					this.getImportacao().getDetalhes().add(this.getNovoErro(fieldError + line, e));
					line++;
				}

			}
			this.getImportacao().setItems(materiais);
			workbook.close();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private String capitalizeFirst(String string) {
		String s = string.toLowerCase();
		return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
	}

	@Override
	protected void detalhes() {
		for (Object m : this.getImportacao().getItems()) {

			Material material = (Material) m;

			try {
				defaultMaterialService.save(material);
				this.getImportacao().getDetalhes().add(getNovoDetalhe(material));

				if (material.getDuplicatedPN())
					this.getImportacao().getDetalhes().add(getNovoDetalhe(material, "importacao.item.duplicado"));

			} catch (Exception e) {
				this.getImportacao().getDetalhes().add(this.getNovoErro(material, e));
			}
		}
	}

	@Override
	protected String getFileName() {
		return "materiais.xls";
	}

}
