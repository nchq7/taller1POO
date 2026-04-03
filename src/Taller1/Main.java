package Taller1;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
// Ignacio Valdivia 22.179.357-9 ICCI
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int mainMenu = -1;
		do { 
			System.out.println("1)Menu de usuarios \n2)Menu de analisis \n3)Salir");
			
			if (scanner.hasNextInt()) {
			    mainMenu = scanner.nextInt();
			    
			    if (mainMenu < 1 || mainMenu > 3) {
			    	System.out.println("Error. Elección invalida");
			    }
			    
			} else {
			    System.out.println("Debes ingresar un numero.");
			    scanner.next();
			}
			
		}while (mainMenu < 1 || mainMenu > 3);
		
		String[] IDs = new String[100];
        String[] Contraseñas = new String[100];
        
        int contadorLogin = 0;
        
		try { // lectura del archivo usuarios
			File archivo = new File("Usuarios.txt");
            Scanner lector = new Scanner(archivo);
            while (lector.hasNextLine()) {
            	String linea = lector.nextLine();
            	String[] partes = linea.split(";");
            	
            	IDs[contadorLogin] = partes[0];
            	Contraseñas[contadorLogin] = partes[1];
            	
            	contadorLogin++;
            	
            }
            lector.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error. No se pudo abrir el archivo.");
			
		}
		// aca termina la carga de IDs y contraseñas
		// aca empieza la carga de actividades
		String[] IDact = new String[300];
		String[] fecha = new String[300];
		int[] horas = new int[300];
		String[] actividad = new String[300];
		int contador = 0;
		
		try { 										//lectura del archivo registros
			File archivo = new File("Registros.txt");
            Scanner lector = new Scanner(archivo);
            while (lector.hasNextLine()) {
            	String linea = lector.nextLine();
            	String[] partes = linea.split(";");
            	
            	IDact[contador] = partes[0];
            	fecha[contador] = partes[1];
            	horas[contador] = Integer.parseInt(partes[2]);
            	actividad[contador] = partes[3];
            	
            	contador++;
            	
            }
            lector.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error. No se pudo abrir el archivo.");
			
		}
		// aca termina la carga de actividades
		// empezamos seleccion de opcion main menu
		if (mainMenu == 1) {
			
			System.out.print("Usuario: ");
			scanner.nextLine();
			String usuarioLogin = scanner.nextLine();
			System.out.print("Contraseña: ");
			String contraLogin = scanner.nextLine();
			boolean acceso = false;
			
			for (int i = 0; i < contadorLogin ; i++) {
				if (IDs[i].equals(usuarioLogin) && Contraseñas[i].equals(contraLogin)){
					System.out.println("Acceso correcto!");
					acceso = true;
					break;
				}
			}
			if (!acceso) {
				System.out.println("Acceso denegado. Usuario o contraseña incorrectos.");
			} 
			
			if (acceso) {
				int opcionUsuario = -1;
			    
				do {
					System.out.println("Bienvenido "+ usuarioLogin + "!");
					System.out.println("Que deseas realizar?");
					System.out.println("1) Registrar actividad.");
					System.out.println("2) Modificar actividad.");
					System.out.println("3) Eliminar actividad.");
					System.out.println("4) Cambiar contraseña.");
					System.out.println("5) Salir.");
					
					if (scanner.hasNextInt()) {
						opcionUsuario = scanner.nextInt();
						if (opcionUsuario < 1 || opcionUsuario > 5) {
					           System.out.println("Error. Opción inválida.");
					       }
					}else {
						System.err.println("Debes ingresar un número.");
						scanner.next();
						opcionUsuario = -1;
					}
					
					
					// PARA ESTA PARTE NECESITO SI O SI LA CARGA DE ARCHIVOS DE ACTIVIDADES
					int contadorActividades;
					int indiceReal;
					int[] indicesUsuario = new int[300];
					
					switch(opcionUsuario) {
						case 1:{
							if (contador >= 300) {
						        System.out.println("No se pueden registrar más actividades.");
						        break;
						    }
							
							System.out.println("Registrar actividad: ");
							int dia;
							int mes;
							int año;
							int duracion;
							do {
								System.out.print("Ingrese día (1-31): ");
								if (scanner.hasNextInt()) {
									dia = scanner.nextInt();
									
									if(dia < 1 || dia > 31) {
										System.err.println("Error. Ingresar días validos");
									}
									
								}else {
									System.err.println("Error. Ingrese solo números.");
									scanner.next();
									dia = -1;
								}
							}while(dia < 1 || dia > 31);
							
							do {
								System.out.print("Ingrese mes (1-12): ");
								if (scanner.hasNextInt()) {
									mes = scanner.nextInt();
									
									if(mes < 1 || mes > 12) {
										System.err.println("Error. Ingresar mes valido");
									}
									
								}else {
									System.err.println("Error. Ingrese solo números.");
									scanner.next();
									mes = -1;
								}
							}while(mes < 1 || mes > 12);
							
							do {
								System.out.print("Ingrese año (0-2100): ");
								if (scanner.hasNextInt()) {
									año = scanner.nextInt();
									
									if(año < 0 || año > 2100) {
										System.err.println("Error. Ingresar año valido");
									}
									
								}else {
									System.err.println("Error. Ingrese solo números.");
									scanner.next();
									año = -1;
								}
							}while(año < 0 || año > 2100);
							
							do {
								System.out.print("Ingrese duración de la actividad: ");
								if (scanner.hasNextInt()) {
									duracion = scanner.nextInt();
									
									if(duracion < 0) {
										System.err.println("Error. No puede ser negativo");
									}
									
								}else {
									System.err.println("Error. Ingrese solo números.");
									scanner.next();
									duracion = -1;
								}
							}while(duracion < 0);
							
							scanner.nextLine();
							System.out.print("Tipo de actividad: ");
							String tipo = scanner.nextLine();
							
							IDact[contador] = usuarioLogin;
							fecha[contador] = dia + "/" + mes + "/" + año;
							horas[contador] = duracion;
							actividad[contador] = tipo;

							contador++;
							try {
							    PrintWriter writer = new PrintWriter("Registros.txt");

							    for (int i = 0; i < contador; i++) {
							        writer.println(IDact[i] + ";" + fecha[i] + ";" + horas[i] + ";" + actividad[i]);
							    }

							    writer.close();
							    System.out.println("Actividad registrada con éxito!");

							} catch (FileNotFoundException e) {
							    System.out.println("Error al guardar el archivo.");
							}
							break;
						}
						case 2:
							
							System.out.println("Que actividad deseas modificar?");
							/* acá hacer un for desde 0 hasta el largo de la lista,
							 * pero que muestre SOLO las actividades de la persona
							 * (usar la variable UsuarioLogin que contiene el nombre)
							*/
							// CONSTRUIR LA LISTA EXCLUSIVA DEL USUARIO LOGEADO
							
							contadorActividades = 0;
							
							System.out.println("0) Regresar");
							for(int i = 0; i < contador; i++) {
								
								if (usuarioLogin.equals(IDact[i])){
									indicesUsuario[contadorActividades] = i;
									System.out.println((contadorActividades +1) +") "+IDact[i]+";"+fecha[i]+";"+horas[i]+";"+actividad[i]);
									contadorActividades += 1;
								} 
							}// término del ciclo for
							if (contadorActividades == 0) {
								System.out.println("No hay actividades registradas.");
								break;
							}

							int opcionModificar;
							do {
								System.out.print("Elige una opción: ");
								
								if (scanner.hasNextInt()) {
									opcionModificar = scanner.nextInt();
									
									if (opcionModificar < 0 || opcionModificar > contadorActividades) {
								           System.out.println("Error. Opción inválida.");
								       }
								}else {
									System.err.println("Debes ingresar un número.");
									scanner.next();
									opcionModificar = -1;
								}
								
							}while(opcionModificar < 0 || opcionModificar > contadorActividades);
							
							if (opcionModificar == 0) {
								System.out.println("Saliendo...");
								break;
							}
							indiceReal = indicesUsuario[opcionModificar-1];
							int opcionMenuModificacion;
							do {
								System.out.println("Que deseas modificar?");
								System.out.println("0) Regresar.");
								System.out.println("1) Fecha");
								System.out.println("2) Duracion");
								System.out.println("3) Tipo de actividad");
								
								if (scanner.hasNextInt()) {
									opcionMenuModificacion = scanner.nextInt();
									scanner.nextLine();
									
									if (opcionMenuModificacion < 0 || opcionMenuModificacion > 3) {
										System.err.println("Opción invalida. intente denuevo.");
									}
								}else {
									System.err.println("Debes ingresar un número.");
									scanner.next();
									opcionMenuModificacion = -1;
								}
								
							}while(opcionMenuModificacion < 0 || opcionMenuModificacion > 3);
							
							switch(opcionMenuModificacion) {
						    case 1:{
						        int dia;
						        int mes;
						        int año;
						        
						        do {
						        	System.out.println("Ingresa día (1-31): ");
						        	
						        	if (scanner.hasNextInt()) {
						        		dia = scanner.nextInt();
						        		if (dia < 1 || dia > 31) {
						        			System.out.println("Día no válido.");
						        		}
						        		
						        	}else {
						        		System.out.println("Error. Debes ingresar un numero.");
						        		scanner.next();
						        		dia = -1;
						        	}
						        }while(dia < 1 || dia > 31);
						        
						        do {
						        	System.out.println("Ingresa mes (1-12): ");
						        	if (scanner.hasNextInt()) {
						        		mes = scanner.nextInt();
						        		if (mes < 1 || mes > 12) {
						        			System.out.println("Mes no valido");
						        		}
						        	}else {
						        		System.out.println("Error. Debes ingresar un numero.");
						        		scanner.next();
						        		mes = -1;
						        	}
						        }while(mes < 1 || mes > 12);
						        
						        do {
						        	System.out.println("Ingrese año: ");
						        	
						        	if (scanner.hasNextInt()) {
						        		año = scanner.nextInt();
						        		if (año <= 0 || año > 2100) {
						        			System.out.println("año no valido");
						        		}
						        	}else {
						        		System.out.println("Error. Debes ingresar un numero.");
						        		scanner.next();
						        		año = -1;
						        	}
						        }while(año <= 0 || año > 2100);
						        
						        scanner.nextLine();
						        String nuevaFecha = dia + "/" + mes + "/" + año;
						        fecha[indiceReal] = nuevaFecha;
						        break;
						    }
						    case 2:
						    	int duracion;
						    	do {
						    		System.out.print("Ingrese nueva duracion: ");
						    		if (scanner.hasNextInt()) {
						    			duracion = scanner.nextInt();
						    			
						    			if (duracion < 0) {
						    				System.out.println("La duración no puede ser negativa.");
						    			}
						    		}else {
						    			System.out.println("Debes ingresar un numero.");
						    			scanner.next();
						    			duracion = -1;
						    		}
						    		
						    		
						    	}while(duracion < 0);
						        
						        
						        horas[indiceReal] = duracion;
						        scanner.nextLine();
						        break;

						    case 3:
						        System.out.print("Ingrese nuevo tipo de actividad: ");
						        actividad[indiceReal] = scanner.nextLine();
						        break;

						    case 0:
						        break;
						}
							// ACÁ AGREGAR LA PARTE MAS IMPORTANTE: LA MODIFICACION DEL TXT
							if (opcionMenuModificacion != 0) {
							    try {
							        PrintWriter writer = new PrintWriter("Registros.txt");

							        for (int i = 0; i < contador; i++) {
							            writer.println(IDact[i] + ";" + fecha[i] + ";" + horas[i] + ";" + actividad[i]);
							        }

							        writer.close();
							        System.out.println("Actividad modificada con exito!");

							    } catch (FileNotFoundException e) {
							        System.out.println("Error al guardar el archivo.");
							    }
							}
							
							
							break;
						case 3:
								System.out.println("Que actividad desea eliminar? ");
								contadorActividades = 0;
								System.out.println("0) Regresar");
								for(int i = 0; i < contador; i++) {
									
									if (usuarioLogin.equals(IDact[i])){
										indicesUsuario[contadorActividades] = i;
										System.out.println((contadorActividades +1) +") "+IDact[i]+";"+fecha[i]+";"+horas[i]+";"+actividad[i]);
										contadorActividades += 1;
									}
								}
								int opcionEliminar = -1;

								do {
								    System.out.print("Elige una opción: ");
								    
								    if (scanner.hasNextInt()) 
								    {
								    	opcionEliminar = scanner.nextInt();
								    
								    
									    if (opcionEliminar < 0 || opcionEliminar > contadorActividades) 
									    {
									        System.err.println("Error. Opción inválida.");
									    }
								    }else 
								    {
								    	System.err.println("Error. Solo ingresar número.");
								    	scanner.next();
								    }

								}while (opcionEliminar < 0 || opcionEliminar > contadorActividades);

								if (opcionEliminar == 0) {
								    break;
								}
								indiceReal = indicesUsuario[opcionEliminar - 1];
								
								for (int i = indiceReal; i < contador - 1; i++) {
								    IDact[i] = IDact[i + 1];
								    fecha[i] = fecha[i + 1];
								    horas[i] = horas[i + 1];
								    actividad[i] = actividad[i + 1];
								}
								
								contador--;
								try {
								    PrintWriter writer = new PrintWriter("Registros.txt");

								    for (int i = 0; i < contador; i++) {
								        writer.println(IDact[i] + ";" + fecha[i] + ";" + horas[i] + ";" + actividad[i]);
								    }

								    writer.close();
								    System.out.println("Actividad eliminada con exito!");

								} catch (FileNotFoundException e) {
								    System.out.println("Error al guardar el archivo.");
								}
								
							break;
						case 4:
							int indiceUsuarioActual = -1;
							for(int i = 0; i < contadorLogin; i++) {
								if (IDs[i].equals(usuarioLogin)) {
									indiceUsuarioActual = i;
									break;
								}
							}
							System.out.println("Ingrese nueva contraseña: ");
							scanner.nextLine();
							String nuevaContra = scanner.nextLine();
							Contraseñas[indiceUsuarioActual] = nuevaContra;
							try {
								PrintWriter writer = new PrintWriter("Usuarios.txt");
								for (int i = 0; i < contadorLogin; i++) {
									writer.println(IDs[i] + ";" + Contraseñas[i]);
								}
								writer.close();
								System.out.println("Tu contraseña ha sido modificada con exito!!");
								
							}catch (FileNotFoundException e){
								System.out.println("Error al guardar el archivo");
							}
							break;
						case 5:
							System.out.println("Saliendo...");
							break;
					}
					
				}while(opcionUsuario != 5);
				
				
			}
			
			
		}else if(mainMenu == 2) {
			int opcionUsuario=-1;
			do {
			
			do {
				System.out.println("Bienvenido al menu de analisis!\n");
				System.out.println("¿Que deseas realizar?\n");
				System.out.println("1) Actividad más realizada\n2) Actividad más realizada por cada usuario\n3) Usuario con mayor procastinacion\n4) Ver todas las actividades\n5) Salir");
				
				if (scanner.hasNextInt()) {
					opcionUsuario = scanner.nextInt();
					
					if (opcionUsuario < 1 || opcionUsuario > 5) {
						System.err.println("Error. Ingresa una opción válida");
					}
					
				}else {
					System.err.println("Error. Ingresa solo números.");
					scanner.next();
					opcionUsuario = -1;
				}
				
			}while(opcionUsuario < 1 || opcionUsuario > 5);
			
			switch(opcionUsuario) {
				case 1: 
					int[] contadorAct= new int[contador];
					String[] actividades = new String[contador];
					int[] horasAct = new int[contador];
					
					int cantidadActividades = 0;
					for(int i = 0; i < contador; i++) {
						
						String actActual = actividad[i];
						boolean existe = false;
						
						for (int j = 0; j < cantidadActividades; j++) {
							// este for recorre la lista de actividades 
							if (actividades[j].equals(actActual)) {
					            contadorAct[j]++;
					            horasAct[j] += horas[i];
					            existe = true;
					            break;
							}
						
						}
						if (!existe) {
					        actividades[cantidadActividades] = actActual;
					        contadorAct[cantidadActividades] = 1;
					        horasAct[cantidadActividades] = horas[i];
					        cantidadActividades++;
					    }
					}
					
					//ahora buscamos la mas realizada nomas
					int max = 0;
					int maxHoras = 0;
					String masRepetida = "";
					for (int i = 0; i < cantidadActividades; i++) {
						if (contadorAct[i] > max) {
							max = contadorAct[i];
							maxHoras = horasAct[i];
							masRepetida = actividades[i];
						}
					}
					
					System.out.println("Actividad mas realizada: " + masRepetida + " (con "+maxHoras+" horas)\n");
					break;
				case 2:
					System.out.println("Actividad mas realizada por cada usuario:");
					
					String[] Usuarios = new String[contadorLogin];
				    String[] actividadUsuario = new String[contadorLogin];
				    int[] horasUsuario = new int[contadorLogin];
					
				    for (int i = 0; i < contadorLogin; i++) { // este for es para CADA USUARIO
				    	
				    	String usuarioActual = IDs[i];
				    	
				    	String[] actividadesTemp = new String[contador];
				    	int[] contadorActTemp = new int[contador];
				    	int[] horasActTemp = new int[contador];
				    	int cantidadActividadesTemp = 0;
				    	
				    	for (int j = 0; j < contador; j++) { // este for es para "recorrer" el archivo buscando el mismo nombre
				    		if (IDact[j].equals(usuarioActual)) {
				    			
				    			String actActual = actividad[j];
				                boolean existe = false;
				                
				                for (int k = 0; k < cantidadActividadesTemp; k++) { // acumula las actividades repetidas
				                	if (actividadesTemp[k].equals(actActual)) {
				                		contadorActTemp[k]++;
				                		horasActTemp[k] += horas[j];
				                		existe = true;
				                		break;
				                	}
				                }
				                if (!existe) {
				                	actividadesTemp[cantidadActividadesTemp] = actActual;
				                	contadorActTemp[cantidadActividadesTemp] = 1;
				                	horasActTemp[cantidadActividadesTemp] = horas[j];
				                	cantidadActividadesTemp++;
				                }
				                
				    		}
				    		
				    	}
				    	int maxTemp = 0; 
				    	int horasMaxTemp = 0; 
				    	String masRepetidaTemp = ""; 
				    	for (int j = 0; j < cantidadActividadesTemp; j++) {
				    		if (contadorActTemp[j] > maxTemp) {
				    			maxTemp = contadorActTemp[j];
				    			horasMaxTemp = horasActTemp[j];
				    			masRepetidaTemp = actividadesTemp[j];
				    		}
				    	}
				    	Usuarios[i] = usuarioActual;
				    	actividadUsuario[i] = masRepetidaTemp;
				    	horasUsuario[i] = horasMaxTemp;
				    	
				    } // termina el for i
				    for (int i = 0; i < contadorLogin; i++) {
				    	System.out.println("* " + Usuarios[i] + " -> " + actividadUsuario[i] + " -> con " + horasUsuario[i] + " horas registradas.");
				    }
				        
					break;
				case 3:
					
					break;
				case 4:
					for(int i =0; i < contador; i++) {
						System.out.println(i+1 + ") "+ IDact[i]+ ";" + fecha[i] + ";" + horas[i] + ";" + actividad[i]);
					}
					break;
				case 5:
					System.out.println("Saliendo...");
					break;	
			}
			}while(opcionUsuario != 5);
		}else {
			System.out.println("Usted salió exitosamente.");
		}
		// aca termina las opciones PRINCIPALES menu principal
		
	}

}
