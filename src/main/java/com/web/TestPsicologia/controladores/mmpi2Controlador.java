package com.web.TestPsicologia.controladores;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Vector;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Controller
public class mmpi2Controlador {

	double exportL;
	double exportF;
	double exportK;
	double exportHs;
	double exportD;
	double exportHy;
	double exportPd;
	double exportMf;
	double exportPa;
	double exportPt;
	double exportSc;
	double exportMa;
	double exportSi;

	int puntosL;
	int puntosF;
	int puntosK;
	int puntosHs;
	int puntosD;
	int puntosHy;
	int puntosPd;
	int puntosMf;
	int puntosPa;
	int puntosPt;
	int puntosSc;
	int puntosMa;
	int puntosSi;

	String exportNombre;
	String exportSexo;
	String exportFecha;

	@GetMapping("/mmpi2")
	public String EstadisticasGetRequest(Model model) throws ClassNotFoundException, SQLException {

		return "mmpi2";
	}

	@PostMapping("/resultadoMmpi2")
	public String mmpi2PostRequest(String resultado, String sexo, String nombrePaciente, String fechaNac) {

		// Matriz con todas las respuestas
		Object[][] respuestas = new Object[370][2];

		for (int i = 0; i < 370; i++) {
			respuestas[i][0] = i + 1;
		}
		for (int i = 0; i < resultado.length(); i++) {
			respuestas[i][1] = resultado.charAt(i);
		}

		System.out.println("");
		for (Object[] row : respuestas)
			System.out.println(Arrays.toString(row));
		System.out.println("");

		// Arreglos de digitos
		Object[] metricaL = { 16, 29, 41, 51, 77, 93, 102, 107, 123, 139, 153, 183, 203, 232, 260 };
		Object[] metricaF = { 18, 24, 30, 36, 42, 48, 54, 60, 66, 72, 84, 96, 114, 138, 144, 150, 156, 162, 168, 180,
				198, 216, 228, 234, 240, 246, 252, 258, 264, 270, 282, 288, 294, 300, 306, 312, 324, 336, 349, 355, 361,
				6, 12, 78, 90, 102, 108, 120, 126, 132, 174, 186, 192, 204, 210, 222, 276, 318, 330, 343 };
		Object[] metricaK = { 83, 29, 37, 58, 76, 110, 116, 122, 127, 130, 136, 148, 157, 158, 167, 171, 196, 213, 243,
				267, 284, 290, 330, 338, 339, 341, 346, 348, 356, 365 };
		Object[] metricaHs = { 18, 28, 39, 53, 59, 97, 101, 111, 149, 175, 247, 2, 3, 8, 10, 20, 45, 47, 57, 91, 117,
				141, 143, 152, 164, 173, 176, 179, 208, 224, 249, 255 };
		Object[] metricaD = { 5, 15, 18, 31, 38, 39, 46, 56, 73, 92, 117, 127, 130, 146, 147, 170, 175, 181, 215, 233,
				2, 9, 10, 20, 29, 33, 37, 43, 45, 49, 55, 68, 75, 76, 95, 109, 118, 134, 140, 141, 142, 143, 148, 165,
				178, 188, 189, 212, 221, 223, 226, 238, 245, 248, 260, 267, 330 };
		Object[] metricaHy = { 11, 18, 31, 39, 40, 44, 65, 101, 166, 172, 175, 218, 230, 2, 3, 7, 8, 9, 10, 14, 26, 29,
				45, 47, 58, 76, 81, 91, 95, 98, 110, 115, 116, 124, 125, 129, 135, 141, 148, 151, 152, 157, 159, 161,
				164, 167, 173, 176, 179, 185, 193, 208, 213, 224, 241, 243, 249, 253, 263, 265 };
		Object[] metricaPd = { 17, 21, 22, 31, 32, 35, 42, 52, 54, 56, 71, 82, 89, 94, 99, 105, 113, 195, 202, 219, 225,
				259, 264, 288, 9, 12, 34, 70, 79, 83, 95, 122, 125, 129, 143, 157, 158, 160, 167, 171, 185, 209, 214,
				217, 226, 243, 261, 263, 266, 267 };
		Object[] metricaMfm = { 4, 25, 62, 64, 67, 74, 80, 112, 119, 122, 128, 137, 166, 177, 187, 191, 196, 205, 209,
				219, 236, 251, 256, 268, 271, 1, 19, 26, 27, 63, 68, 69, 76, 86, 103, 104, 107, 120, 121, 132, 133, 163,
				184, 193, 194, 197, 199, 201, 207, 231, 235, 237, 239, 254, 257, 272 };
		Object[] metricaMff = { 4, 25, 62, 64, 67, 74, 80, 112, 119, 121, 122, 128, 137, 177, 187, 191, 196, 205, 219,
				236, 251, 256, 271, 1, 19, 26, 27, 63, 68, 69, 76, 86, 103, 104, 107, 120, 132, 133, 163, 166, 184, 193,
				194, 197, 199, 201, 207, 209, 231, 235, 237, 239, 254, 257, 268, 272 };
		Object[] metricaPa = { 16, 17, 22, 23, 24, 42, 99, 113, 138, 144, 145, 146, 162, 234, 259, 271, 277, 285, 305,
				307, 333, 334, 336, 355, 361, 81, 95, 98, 100, 104, 110, 244, 255, 266, 283, 284, 286, 297, 314, 315 };
		Object[] metricaPt = { 11, 16, 23, 31, 38, 56, 65, 73, 82, 89, 94, 130, 147, 170, 175, 196, 218, 242, 273, 275,
				277, 285, 289, 301, 302, 304, 308, 309, 310, 313, 316, 317, 320, 325, 326, 327, 328, 329, 331, 3, 9, 33,
				109, 140, 165, 174, 293, 321 };
		Object[] metricaSc = { 16, 17, 21, 22, 23, 31, 32, 35, 38, 42, 44, 46, 48, 65, 85, 92, 138, 145, 147, 166, 168,
				170, 180, 277, 279, 281, 287, 182, 291, 190, 218, 292, 296, 221, 298, 229, 299, 233, 234, 242, 303, 307,
				311, 247, 252, 256, 268, 316, 319, 320, 322, 273, 323, 274, 325, 329, 332, 333, 355, 6, 9, 12, 34, 90,
				91, 106, 165, 177, 179, 192, 210, 255, 276, 278, 280, 290, 295, 343 };
		Object[] metricaMa = { 13, 15, 21, 23, 50, 55, 61, 85, 87, 98, 113, 122, 131, 145, 155, 168, 169, 182, 190, 200,
				205, 206, 211, 212, 218, 220, 227, 229, 238, 242, 244, 248, 250, 253, 269, 88, 93, 100, 106, 107, 136,
				154, 158, 167, 243, 263 };
		Object[] metricaSi = { 31, 56, 70, 100, 104, 110, 127, 135, 158, 161, 167, 185, 215, 243, 251, 265, 275, 284,
				289, 296, 302, 308, 326, 337, 338, 347, 348, 351, 352, 357, 364, 367, 368, 369, 25, 32, 49, 79, 86, 106,
				112, 131, 181, 189, 207, 209, 231, 237, 255, 262, 267, 280, 321, 328, 335, 340, 342, 344, 345, 350, 353,
				354, 358, 359, 360, 362, 363, 366, 370 };

		Object[][] clavesL = new Object[15][3];
		Object[][] clavesF = new Object[60][3];
		Object[][] clavesK = new Object[30][3];
		Object[][] clavesHs = new Object[32][3];
		Object[][] clavesD = new Object[57][3];
		Object[][] clavesHy = new Object[60][3];
		Object[][] clavesPd = new Object[50][3];
		Object[][] clavesMfm = new Object[56][3];
		Object[][] clavesMff = new Object[56][3];
		Object[][] clavesPa = new Object[40][3];
		Object[][] clavesPt = new Object[48][3];
		Object[][] clavesSc = new Object[78][3];
		Object[][] clavesMa = new Object[46][3];
		Object[][] clavesSi = new Object[69][3];

		// L
		for (int i = 0; i < 15; i++) {
			clavesL[i][2] = 'F';
		}
		int contL = 0;
		for (int x = 0; x < metricaL.length; x++) {
			clavesL[contL][0] = respuestas[(int) metricaL[x] - 1][0];
			clavesL[contL][1] = respuestas[(int) metricaL[x] - 1][1];
			contL = contL + 1;
		}
		for (int j = 0; j < clavesL.length; j++) {
			if (clavesL[j][1].equals(clavesL[j][2])) {
				puntosL = puntosL + 1;
			}
		}

		// F
		for (int i = 0; i < 41; i++) {
			clavesF[i][2] = 'V';
		}
		for (int i = 41; i < 60; i++) {
			clavesF[i][2] = 'F';
		}
		int contF = 0;
		for (int x = 0; x < metricaF.length; x++) {
			clavesF[contF][0] = respuestas[(int) metricaF[x] - 1][0];
			clavesF[contF][1] = respuestas[(int) metricaF[x] - 1][1];
			contF = contF + 1;
		}

		for (int j = 0; j < clavesF.length; j++) {
			if (clavesF[j][1].equals(clavesF[j][2])) {
				puntosF = puntosF + 1;
			}
		}

		// K
		for (int i = 0; i < 1; i++) {
			clavesK[i][2] = 'V';
		}
		for (int i = 1; i < 30; i++) {
			clavesK[i][2] = 'F';
		}
		int contK = 0;
		for (int x = 0; x < metricaK.length; x++) {
			clavesK[contK][0] = respuestas[(int) metricaK[x] - 1][0];
			clavesK[contK][1] = respuestas[(int) metricaK[x] - 1][1];
			contK = contK + 1;
		}
		for (int j = 0; j < clavesK.length; j++) {
			if (clavesK[j][1].equals(clavesK[j][2])) {
				puntosK = puntosK + 1;
			}
		}

		// Hs
		for (int i = 0; i < 11; i++) {
			clavesHs[i][2] = 'V';
		}
		for (int i = 11; i < 32; i++) {
			clavesHs[i][2] = 'F';
		}
		int contHs = 0;
		for (int x = 0; x < metricaHs.length; x++) {
			clavesHs[contHs][0] = respuestas[(int) metricaHs[x] - 1][0];
			clavesHs[contHs][1] = respuestas[(int) metricaHs[x] - 1][1];
			contHs = contHs + 1;
		}
		for (int j = 0; j < clavesHs.length; j++) {
			if (clavesHs[j][1].equals(clavesHs[j][2])) {
				puntosHs = puntosHs + 1;
			}
		}

		// D
		for (int i = 0; i < 20; i++) {
			clavesD[i][2] = 'V';
		}
		for (int i = 20; i < 57; i++) {
			clavesD[i][2] = 'F';
		}
		int contD = 0;
		for (int x = 0; x < metricaD.length; x++) {
			clavesD[contD][0] = respuestas[(int) metricaD[x] - 1][0];
			clavesD[contD][1] = respuestas[(int) metricaD[x] - 1][1];
			contD = contD + 1;
		}
		for (int j = 0; j < clavesD.length; j++) {
			if (clavesD[j][1].equals(clavesD[j][2])) {
				puntosD = puntosD + 1;
			}
		}

		// Hy
		for (int i = 0; i < 13; i++) {
			clavesHy[i][2] = 'V';
		}
		for (int i = 13; i < 60; i++) {
			clavesHy[i][2] = 'F';
		}
		int contHy = 0;
		for (int x = 0; x < metricaHy.length; x++) {
			clavesHy[contHy][0] = respuestas[(int) metricaHy[x] - 1][0];
			clavesHy[contHy][1] = respuestas[(int) metricaHy[x] - 1][1];
			contHy = contHy + 1;
		}
		for (int j = 0; j < clavesHy.length; j++) {
			if (clavesHy[j][1].equals(clavesHy[j][2])) {
				puntosHy = puntosHy + 1;
			}
		}

		System.out.println("");
		for (Object[] row : clavesHy)
			System.out.println(Arrays.toString(row));
		System.out.println("");

		// Pd
		for (int i = 0; i < 24; i++) {
			clavesPd[i][2] = 'V';
		}
		for (int i = 24; i < 50; i++) {
			clavesPd[i][2] = 'F';
		}
		int contPd = 0;
		for (int x = 0; x < metricaPd.length; x++) {
			clavesPd[contPd][0] = respuestas[(int) metricaPd[x] - 1][0];
			clavesPd[contPd][1] = respuestas[(int) metricaPd[x] - 1][1];
			contPd = contPd + 1;
		}
		for (int j = 0; j < clavesPd.length; j++) {
			if (clavesPd[j][1].equals(clavesPd[j][2])) {
				puntosPd = puntosPd + 1;
			}
		}

		// Mfm
		for (int i = 0; i < 25; i++) {
			clavesMfm[i][2] = 'V';
		}
		for (int i = 25; i < 56; i++) {
			clavesMfm[i][2] = 'F';
		}
		int contMfm = 0;
		for (int x = 0; x < metricaMfm.length; x++) {
			clavesMfm[contMfm][0] = respuestas[(int) metricaMfm[x] - 1][0];
			clavesMfm[contMfm][1] = respuestas[(int) metricaMfm[x] - 1][1];
			contMfm = contMfm + 1;
		}
		int puntosMfm = 0;
		for (int j = 0; j < clavesMfm.length; j++) {
			if (clavesMfm[j][1].equals(clavesMfm[j][2])) {
				puntosMfm = puntosMfm + 1;
			}
		}

		// Mf
		for (int i = 0; i < 23; i++) {
			clavesMff[i][2] = 'V';
		}
		for (int i = 23; i < 56; i++) {
			clavesMff[i][2] = 'F';
		}
		int contMff = 0;
		for (int x = 0; x < metricaMff.length; x++) {
			clavesMff[contMff][0] = respuestas[(int) metricaMff[x] - 1][0];
			clavesMff[contMff][1] = respuestas[(int) metricaMff[x] - 1][1];
			contMff = contMff + 1;
		}
		int puntosMff = 0;
		for (int j = 0; j < clavesMff.length; j++) {
			if (clavesMff[j][1].equals(clavesMff[j][2])) {
				puntosMff = puntosMff + 1;
			}
		}

		if (sexo.toString().equals("MASCULINO")) {
			puntosMf = puntosMfm;
		} else if (sexo.toString().equals("FEMENINO")) {
			puntosMf = puntosMff;
		}

		// Pa
		for (int i = 0; i < 25; i++) {
			clavesPa[i][2] = 'V';
		}
		for (int i = 25; i < 40; i++) {
			clavesPa[i][2] = 'F';
		}
		int contPa = 0;
		for (int x = 0; x < metricaPa.length; x++) {
			clavesPa[contPa][0] = respuestas[(int) metricaPa[x] - 1][0];
			clavesPa[contPa][1] = respuestas[(int) metricaPa[x] - 1][1];
			contPa = contPa + 1;
		}
		for (int j = 0; j < clavesPa.length; j++) {
			if (clavesPa[j][1].equals(clavesPa[j][2])) {
				puntosPa = puntosPa + 1;
			}
		}

		// Pt
		for (int i = 0; i < 39; i++) {
			clavesPt[i][2] = 'V';
		}
		for (int i = 39; i < 48; i++) {
			clavesPt[i][2] = 'F';
		}
		int contPt = 0;
		for (int x = 0; x < metricaPt.length; x++) {
			clavesPt[contPt][0] = respuestas[(int) metricaPt[x] - 1][0];
			clavesPt[contPt][1] = respuestas[(int) metricaPt[x] - 1][1];
			contPt = contPt + 1;
		}
		for (int j = 0; j < clavesPt.length; j++) {
			if (clavesPt[j][1].equals(clavesPt[j][2])) {
				puntosPt = puntosPt + 1;
			}
		}

		// Sc
		for (int i = 0; i < 59; i++) {
			clavesSc[i][2] = 'V';
		}
		for (int i = 59; i < 78; i++) {
			clavesSc[i][2] = 'F';
		}
		int contSc = 0;
		for (int x = 0; x < metricaSc.length; x++) {
			clavesSc[contSc][0] = respuestas[(int) metricaSc[x] - 1][0];
			clavesSc[contSc][1] = respuestas[(int) metricaSc[x] - 1][1];
			contSc = contSc + 1;
		}
		for (int j = 0; j < clavesSc.length; j++) {
			if (clavesSc[j][1].equals(clavesSc[j][2])) {
				puntosSc = puntosSc + 1;
			}
		}

		// Ma
		for (int i = 0; i < 35; i++) {
			clavesMa[i][2] = 'V';
		}
		for (int i = 35; i < 46; i++) {
			clavesMa[i][2] = 'F';
		}
		int contMa = 0;
		for (int x = 0; x < metricaMa.length; x++) {
			clavesMa[contMa][0] = respuestas[(int) metricaMa[x] - 1][0];
			clavesMa[contMa][1] = respuestas[(int) metricaMa[x] - 1][1];
			contMa = contMa + 1;
		}
		for (int j = 0; j < clavesMa.length; j++) {
			if (clavesMa[j][1].equals(clavesMa[j][2])) {
				puntosMa = puntosMa + 1;
			}
		}

		// Si
		for (int i = 0; i < 34; i++) {
			clavesSi[i][2] = 'V';
		}
		for (int i = 34; i < 69; i++) {
			clavesSi[i][2] = 'F';
		}
		int contSi = 0;
		for (int x = 0; x < metricaSi.length; x++) {
			clavesSi[contSi][0] = respuestas[(int) metricaSi[x] - 1][0];
			clavesSi[contSi][1] = respuestas[(int) metricaSi[x] - 1][1];
			contSi = contSi + 1;
		}
		for (int j = 0; j < clavesSi.length; j++) {
			if (clavesSi[j][1].equals(clavesSi[j][2])) {
				puntosSi = puntosSi + 1;
			}
		}

		Vector<Number> respuestaFinal = new Vector<Number>();

		respuestaFinal = proceso(sexo, puntosL, puntosF, puntosK, puntosHs, puntosD, puntosHy, puntosPd, puntosMfm,
				puntosMff, puntosPa, puntosPt, puntosSc, puntosMa, puntosSi);

		exportL = respuestaFinal.elementAt(0).doubleValue();
		exportF = respuestaFinal.elementAt(1).doubleValue();
		exportK = respuestaFinal.elementAt(2).doubleValue();
		exportHs = respuestaFinal.elementAt(3).doubleValue();
		exportD = respuestaFinal.elementAt(4).doubleValue();
		exportHy = respuestaFinal.elementAt(5).doubleValue();
		exportPd = respuestaFinal.elementAt(6).doubleValue();
		exportMf = respuestaFinal.elementAt(7).doubleValue();
		exportPa = respuestaFinal.elementAt(8).doubleValue();
		exportPt = respuestaFinal.elementAt(9).doubleValue();
		exportSc = respuestaFinal.elementAt(10).doubleValue();
		exportMa = respuestaFinal.elementAt(11).doubleValue();
		exportSi = respuestaFinal.elementAt(12).doubleValue();

		exportSexo = sexo;
		exportNombre = nombrePaciente;
		exportFecha = calcularEdad(fechaNac);

		return "redirect:/resultadoMmpi2";

	}

	@GetMapping("/resultadoMmpi2")
	public String resultadoMmpi2GetRequest(Model model) throws ClassNotFoundException, SQLException {

		model.addAttribute("exportSexo", exportSexo);
		model.addAttribute("exportNombre", exportNombre);
		model.addAttribute("exportFecha", exportFecha);
		model.addAttribute("exportL", exportL);
		model.addAttribute("exportF", exportF);
		model.addAttribute("exportK", exportK);
		model.addAttribute("exportHs", exportHs);
		model.addAttribute("exportD", exportD);
		model.addAttribute("exportHy", exportHy);
		model.addAttribute("exportPd", exportPd);
		model.addAttribute("exportMf", exportMf);
		model.addAttribute("exportPa", exportPa);
		model.addAttribute("exportPt", exportPt);
		model.addAttribute("exportSc", exportSc);
		model.addAttribute("exportMa", exportMa);
		model.addAttribute("exportSi", exportSi);

		model.addAttribute("puntosL", puntosL);
		model.addAttribute("puntosF", puntosF);
		model.addAttribute("puntosK", puntosK);
		model.addAttribute("puntosHs", puntosHs);
		model.addAttribute("puntosD", puntosD);
		model.addAttribute("puntosHy", puntosHy);
		model.addAttribute("puntosPd", puntosPd);
		model.addAttribute("puntosMf", puntosMf);
		model.addAttribute("puntosPa", puntosPa);
		model.addAttribute("puntosPt", puntosPt);
		model.addAttribute("puntosSc", puntosSc);
		model.addAttribute("puntosMa", puntosMa);
		model.addAttribute("puntosSi", puntosSi);

		return "resultadoMmpi2";
	}

	public Vector<Number> proceso(String sex, int L, int F, int K, int Hs, int D, int Hy, int Pd, int Mfm, int Mff,
			int Ps, int Pt, int Sc, int Ma, int Si) {

		Object[][] tablaAuxiliar = new Object[74][27];
		Vector<Number> respuesta = new Vector<Number>();

		Object[] MasL = { 35, 39, 43, 48, 52, 56, 61, 65, 70, 74, 78, 83, 87, 91, 96, 100 };
		Object[] MasF = { 36, 39, 42, 45, 48, 51, 55, 58, 61, 64, 67, 70, 73, 76, 79, 82, 85, 92, 95, 98, 98, 101, 104,
				107, 110, 113, 116, 119, 120 };
		Object[] MasK = { 0, 0, 0, 0, 0, 0, 30, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 54, 56, 58, 60, 62, 64, 66, 68,
				70, 72, 75, 77, 79, 81 };
		Object[] MasHis = { 0, 0, 30, 31, 31, 32, 33, 35, 37, 39, 42, 45, 48, 51, 54, 57, 59, 62, 64, 66, 68, 70, 73,
				75, 77, 79, 81, 84, 86, 88, 90, 92, 94, 97, 99, 101, 103, 105, 108, 110, 112, 114, 116, 119, 120 };
		Object[] MasD = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 32, 34, 36, 38, 40, 42, 45, 47, 50, 52, 54, 57, 59, 61, 62, 64,
				66, 68, 70, 72, 74, 76, 78, 80, 81, 83, 85, 87, 89, 91, 93, 95, 97, 98, 100, 102, 104, 106, 108, 110,
				112, 114, 115, 117, 119, 120 };
		Object[] MasHy = { 0, 0, 0, 0, 0, 0, 0, 0, 30, 31, 32, 33, 34, 35, 37, 38, 40, 42, 43, 45, 47, 50, 52, 54, 57,
				59, 61, 64, 66, 69, 71, 74, 76, 79, 81, 84, 86, 89, 91, 94, 96, 99, 101, 104, 106, 109, 111, 114, 116,
				119, 120 };
		Object[] MasPd = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 31, 33, 34, 35, 37, 39, 40, 42, 44, 46, 48, 50, 52, 54,
				57, 59, 62, 64, 67, 69, 72, 74, 77, 79, 82, 84, 87, 90, 92, 95, 97, 100, 102, 105, 107, 110, 112, 115,
				117, 120 };
		Object[] MasMf = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50,
				52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 79, 81, 83, 85, 87, 89, 91, 93, 95, 97, 99, 101,
				103, 105, 107, 109 };
		Object[] MasPa = { 0, 0, 30, 31, 32, 34, 37, 39, 42, 46, 49, 53, 57, 61, 64, 68, 72, 75, 79, 83, 86, 90, 94, 97,
				101, 105, 108, 112, 116, 119, 120 };
		Object[] MasPt = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 31, 32, 33, 34, 36, 37, 39, 41, 43, 44, 47, 49,
				51, 53, 55, 57, 59, 62, 64, 66, 68, 70, 72, 74, 77, 79, 81, 83, 85, 87, 89, 91, 94, 96, 98, 100, 102,
				104, 106, 109, 111, 113, 115, 117, 119, 120 };
		Object[] MasSc = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 31, 32, 33, 34, 35, 36, 37, 38, 40, 42, 44, 47, 49,
				51, 53, 54, 55, 56, 58, 60, 62, 63, 65, 67, 69, 70, 72, 74, 75, 77, 79, 81, 82, 84, 86, 87, 89, 91, 93,
				94, 96, 98, 99, 101, 103, 105, 106, 108, 110, 111, 113, 115, 117, 118, 120 };
		Object[] MasMa = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 31, 33, 35, 36, 38, 39, 41, 43, 45, 47, 49, 51, 53, 56, 59,
				62, 65, 69, 72, 75, 78, 81, 85, 88, 91, 94, 98, 101, 104, 107, 110, 114, 117, 120 };
		Object[] MasSi = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 31, 33, 34, 35, 37, 37, 38, 40, 41, 42, 43, 44, 45, 47, 48,
				49, 50, 51, 52, 54, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 68, 69, 70, 71, 72, 73, 75, 76, 77, 78,
				79, 80, 82, 83, 84, 85, 86, 87, 89, 90, 91, 92, 93, 94, 96, 97, 98, 99, 100 };
		Object[] FemL = { 33, 38, 43, 47, 52, 57, 62, 66, 71, 76, 81, 86, 90, 95, 100, 103 };
		Object[] FemF = { 37, 41, 44, 48, 51, 53, 58, 61, 65, 68, 72, 75, 79, 82, 85, 89, 93, 96, 99, 103, 106, 109,
				113, 115, 120 };
		Object[] FemK = { 0, 0, 0, 0, 0, 0, 30, 32, 35, 37, 39, 41, 43, 46, 48, 50, 52, 54, 56, 59, 61, 63, 65, 67, 70,
				72, 74, 76, 78, 81, 83 };
		Object[] FemHis = { 0, 0, 0, 0, 0, 0, 30, 33, 35, 38, 40, 43, 46, 49, 51, 54, 57, 59, 61, 63, 65, 67, 69, 71,
				74, 76, 78, 80, 82, 84, 86, 88, 90, 92, 95, 97, 99, 101, 103, 105, 107, 109, 111, 113, 116, 118, 120 };
		Object[] FemD = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 32, 34, 36, 38, 40, 42, 44, 46, 47, 49, 51, 53, 55, 57, 59,
				62, 64, 66, 68, 70, 72, 75, 77, 79, 81, 83, 86, 88, 90, 92, 94, 96, 99, 101, 103, 105, 107, 109, 112,
				114, 116, 118, 120 };
		Object[] FemHy = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 31, 32, 32, 34, 35, 36, 38, 39, 41, 43, 45, 47, 49, 51, 54,
				56, 58, 61, 63, 65, 68, 70, 73, 75, 77, 80, 82, 84, 87, 89, 92, 94, 96, 99, 101, 104, 106, 108, 111,
				113, 115, 118, 120 };
		Object[] FemPd = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 32, 34, 36, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55,
				58, 60, 63, 66, 68, 71, 73, 76, 79, 81, 84, 87, 89, 92, 94, 97, 100, 102, 105, 107, 110, 113, 115, 118,
				120 };
		Object[] FemMf = { 0, 0, 0, 0, 0, 0, 0, 30, 33, 35, 38, 40, 43, 45, 47, 50, 52, 55, 57, 60, 62, 65, 67, 69, 72,
				74, 77, 79, 82, 84, 87, 89, 92, 94, 96, 99, 101, 104, 106, 109, 111, 114, 116, 118, 120 };
		Object[] FemPa = { 0, 0, 30, 31, 32, 34, 37, 39, 42, 45, 49, 52, 56, 59, 63, 67, 70, 74, 78, 81, 85, 89, 92, 96,
				100, 103, 107, 111, 114, 118, 120 };
		Object[] FemPt = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 31, 32, 34, 35, 37, 39, 40, 42, 44, 47,
				49, 51, 53, 55, 57, 59, 61, 62, 64, 66, 68, 70, 72, 73, 75, 77, 79, 81, 83, 84, 86, 88, 90, 92, 94, 95,
				97, 99, 101, 103, 105, 106, 108, 111, 112, 114, 115, 117, 119, 120 };
		Object[] FemSc = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 31, 32, 33, 34, 36, 37, 39, 41, 42, 44, 46, 48,
				50, 52, 53, 55, 57, 59, 60, 62, 63, 65, 66, 67, 69, 70, 72, 73, 75, 76, 78, 79, 81, 82, 84, 85, 87, 88,
				90, 91, 93, 94, 96, 97, 99, 100, 102, 103, 105, 106, 108, 109, 111, 112, 113, 115, 116, 118, 119, 120 };
		Object[] FemMa = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 56, 59, 62,
				65, 68, 71, 74, 76, 79, 83, 85, 88, 91, 94, 97, 100, 103, 106, 109, 112, 115, 118, 120 };
		Object[] FemSi = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 45, 46, 47,
				48, 49, 50, 51, 52, 53, 54, 55, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 69, 70, 71, 72, 73, 74, 75,
				76, 77, 78, 79, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95 };

		for (int i = 0; i < tablaAuxiliar.length; i++) {
			tablaAuxiliar[i][0] = i;
		}

		for (int i = 0; i < MasL.length; i++) {
			tablaAuxiliar[i][1] = MasL[i];
		}
		for (int i = 0; i < MasF.length; i++) {
			tablaAuxiliar[i][2] = MasF[i];
		}
		for (int i = 0; i < MasK.length; i++) {
			tablaAuxiliar[i][3] = MasK[i];
		}
		for (int i = 0; i < MasHis.length; i++) {
			tablaAuxiliar[i][4] = MasHis[i];
		}
		for (int i = 0; i < MasD.length; i++) {
			tablaAuxiliar[i][5] = MasD[i];
		}
		for (int i = 0; i < MasHy.length; i++) {
			tablaAuxiliar[i][6] = MasHy[i];
		}
		for (int i = 0; i < MasPd.length; i++) {
			tablaAuxiliar[i][7] = MasPd[i];
		}
		for (int i = 0; i < MasMf.length; i++) {
			tablaAuxiliar[i][8] = MasMf[i];
		}
		for (int i = 0; i < MasPa.length; i++) {
			tablaAuxiliar[i][9] = MasPa[i];
		}
		for (int i = 0; i < MasPt.length; i++) {
			tablaAuxiliar[i][10] = MasPt[i];
		}
		for (int i = 0; i < MasSc.length; i++) {
			tablaAuxiliar[i][11] = MasSc[i];
		}
		for (int i = 0; i < MasMa.length; i++) {
			tablaAuxiliar[i][12] = MasMa[i];
		}
		for (int i = 0; i < MasSi.length; i++) {
			tablaAuxiliar[i][13] = MasSi[i];
		}
		for (int i = 0; i < FemL.length; i++) {
			tablaAuxiliar[i][14] = FemL[i];
		}
		for (int i = 0; i < FemF.length; i++) {
			tablaAuxiliar[i][15] = FemF[i];
		}
		for (int i = 0; i < FemK.length; i++) {
			tablaAuxiliar[i][16] = FemK[i];
		}
		for (int i = 0; i < FemHis.length; i++) {
			tablaAuxiliar[i][17] = FemHis[i];
		}
		for (int i = 0; i < FemD.length; i++) {
			tablaAuxiliar[i][18] = FemD[i];
		}
		for (int i = 0; i < FemHy.length; i++) {
			tablaAuxiliar[i][19] = FemHy[i];
		}
		for (int i = 0; i < FemPd.length; i++) {
			tablaAuxiliar[i][20] = FemPd[i];
		}
		for (int i = 0; i < FemMf.length; i++) {
			tablaAuxiliar[i][21] = FemMf[i];
		}
		for (int i = 0; i < FemPa.length; i++) {
			tablaAuxiliar[i][22] = FemPa[i];
		}
		for (int i = 0; i < FemPt.length; i++) {
			tablaAuxiliar[i][23] = FemPt[i];
		}
		for (int i = 0; i < FemSc.length; i++) {
			tablaAuxiliar[i][24] = FemSc[i];
		}
		for (int i = 0; i < FemMa.length; i++) {
			tablaAuxiliar[i][25] = FemMa[i];
		}
		for (int i = 0; i < FemSi.length; i++) {
			tablaAuxiliar[i][26] = FemSi[i];
		}

		for (int i = 0; i < tablaAuxiliar.length; i++) {
			for (int j = 0; j < tablaAuxiliar[i].length; j++)
				if (tablaAuxiliar[i][j] == null) {
					tablaAuxiliar[i][j] = 0;
				}
		}

		// SACO VALORES

		int valorMasL = 0;
		int valorMasF = 0;
		int valorMasK = 0;
		double valorMasHis = 0;
		int valorMasD = 0;
		int valorMasHy = 0;
		double valorMasPd = 0;
		int valorMasMf = 0;
		int valorMasPa = 0;
		int valorMasPt = 0;
		int valorMasSc = 0;
		double valorMasMa = 0;
		int valorMasSi = 0;
		int valorFemL = 0;
		int valorFemF = 0;
		int valorFemK = 0;
		double valorFemHis = 0;
		int valorFemD = 0;
		int valorFemHy = 0;
		double valorFemPd = 0;
		int valorFemMf = 0;
		int valorFemPa = 0;
		int valorFemPt = 0;
		int valorFemSc = 0;
		double valorFemMa = 0;
		int valorFemSi = 0;

		if (sex.toString().equals("MASCULINO")) {
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(L)) {
					valorMasL = (int) tablaAuxiliar[i][1];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(F)) {
					valorMasF = (int) tablaAuxiliar[i][2];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(K)) {
					valorMasK = (int) tablaAuxiliar[i][3];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Hs)) {
					valorMasHis = (int) tablaAuxiliar[i][4];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(D)) {
					valorMasD = (int) tablaAuxiliar[i][5];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Hy)) {
					valorMasHy = (int) tablaAuxiliar[i][6];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Pd)) {
					valorMasPd = (int) tablaAuxiliar[i][7];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Mfm)) {
					valorMasMf = (int) tablaAuxiliar[i][8];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Ps)) {
					valorMasPa = (int) tablaAuxiliar[i][9];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Pt)) {
					valorMasPt = (int) tablaAuxiliar[i][10];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Sc)) {
					valorMasSc = (int) tablaAuxiliar[i][11];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Ma)) {
					valorMasMa = (int) tablaAuxiliar[i][12];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Si)) {
					valorMasSi = (int) tablaAuxiliar[i][13];
				}
			}

			valorMasHis = valorMasHis + (0.5 * valorMasK);
			valorMasPd = valorMasPd + (0.4 * valorMasK);
			valorMasPt = valorMasPt + (1 * valorMasK);
			valorMasSc = valorMasSc + (1 * valorMasK);
			valorMasMa = valorMasMa + (0.2 * valorMasK);

			/*
			 * System.out.println(""); System.out.println("*************************");
			 * System.out.println("MASCULINO: DESPUES DE LAS MULTIPLICACIONES:");
			 * System.out.println(""); System.out.println("EL VALOR DE L ES DE: " +
			 * valorMasL); System.out.println("EL VALOR DE F ES DE: " + valorMasF);
			 * System.out.println("EL VALOR DE K ES DE: " + valorMasK);
			 * System.out.println("EL VALOR DE Hs ES DE: " + valorMasHis);
			 * System.out.println("EL VALOR DE D ES DE: " + valorMasD);
			 * System.out.println("EL VALOR DE Hy ES DE: " + valorMasHy);
			 * System.out.println("EL VALOR DE Pd ES DE: " + valorMasPd);
			 * System.out.println("EL VALOR DE Mf ES DE: " + valorMasMf);
			 * System.out.println("EL VALOR DE Pa ES DE: " + valorMasPa);
			 * System.out.println("EL VALOR DE Pt ES DE: " + valorMasPt);
			 * System.out.println("EL VALOR DE Sc ES DE: " + valorMasSc);
			 * System.out.println("EL VALOR DE Ma ES DE: " + valorMasMa);
			 * System.out.println("EL VALOR DE Si ES DE: " + valorMasSi);
			 */

			respuesta.add(valorMasL);
			respuesta.add(valorMasF);
			respuesta.add(valorMasK);
			respuesta.add(valorMasHis);
			respuesta.add(valorMasD);
			respuesta.add(valorMasHy);
			respuesta.add(valorMasPd);
			respuesta.add(valorMasMf);
			respuesta.add(valorMasPa);
			respuesta.add(valorMasPt);
			respuesta.add(valorMasSc);
			respuesta.add(valorMasMa);
			respuesta.add(valorMasSi);

		}

		if (sex.toString().equals("FEMENINO")) {
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(L)) {
					valorFemL = (int) tablaAuxiliar[i][1];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(F)) {
					valorFemF = (int) tablaAuxiliar[i][2];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(K)) {
					valorFemK = (int) tablaAuxiliar[i][3];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Hs)) {
					valorFemHis = (int) tablaAuxiliar[i][4];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(D)) {
					valorFemD = (int) tablaAuxiliar[i][5];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Hy)) {
					valorFemHy = (int) tablaAuxiliar[i][6];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Pd)) {
					valorFemPd = (int) tablaAuxiliar[i][7];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Mff)) {
					valorFemMf = (int) tablaAuxiliar[i][8];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Ps)) {
					valorFemPa = (int) tablaAuxiliar[i][9];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Pt)) {
					valorFemPt = (int) tablaAuxiliar[i][10];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Sc)) {
					valorFemSc = (int) tablaAuxiliar[i][11];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Ma)) {
					valorFemMa = (int) tablaAuxiliar[i][12];
				}
			}
			for (int i = 0; i < tablaAuxiliar.length; i++) {
				if (tablaAuxiliar[i][0] == Integer.valueOf(Si)) {
					valorFemSi = (int) tablaAuxiliar[i][13];
				}
			}

			valorFemHis = valorFemHis + (0.5 * valorFemK);
			valorFemPd = valorFemPd + (0.4 * valorFemK);
			valorFemPt = valorFemPt + (1 * valorFemK);
			valorFemSc = valorFemSc + (1 * valorFemK);
			valorFemMa = valorFemMa + (0.2 * valorFemK);

			/*
			 * System.out.println(""); System.out.println("*************************");
			 * System.out.println("DESPUES DE LAS MULTIPLICACIONES:");
			 * System.out.println(""); System.out.println("EL VALOR DE L ES DE: " +
			 * valorFemL); System.out.println("EL VALOR DE F ES DE: " + valorFemF);
			 * System.out.println("EL VALOR DE K ES DE: " + valorFemK);
			 * System.out.println("EL VALOR DE Hs ES DE: " + valorFemHis);
			 * System.out.println("EL VALOR DE D ES DE: " + valorFemD);
			 * System.out.println("EL VALOR DE Hy ES DE: " + valorFemHy);
			 * System.out.println("EL VALOR DE Pd ES DE: " + valorFemPd);
			 * System.out.println("EL VALOR DE Mf ES DE: " + valorFemMf);
			 * System.out.println("EL VALOR DE Pa ES DE: " + valorFemPa);
			 * System.out.println("EL VALOR DE Pt ES DE: " + valorFemPt);
			 * System.out.println("EL VALOR DE Sc ES DE: " + valorFemSc);
			 * System.out.println("EL VALOR DE Ma ES DE: " + valorFemMa);
			 * System.out.println("EL VALOR DE Si ES DE: " + valorFemSi);
			 */

			respuesta.add(valorFemL);
			respuesta.add(valorFemF);
			respuesta.add(valorFemK);
			respuesta.add(valorFemHis);
			respuesta.add(valorFemD);
			respuesta.add(valorFemHy);
			respuesta.add(valorFemPd);
			respuesta.add(valorFemMf);
			respuesta.add(valorFemPa);
			respuesta.add(valorFemPt);
			respuesta.add(valorFemSc);
			respuesta.add(valorFemMa);
			respuesta.add(valorFemSi);

		}

		return respuesta;

		// imprimirMatriz(tablaAuxiliar);

	}

	public String calcularEdad(String fecha) {

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fechaNac = LocalDate.parse(fecha, fmt);
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);
		String edad = (periodo.getYears() + " AÑOS , " + periodo.getMonths() + " MESES , " + periodo.getDays()
				+ " DÍAS");

		return edad;

	}
}
