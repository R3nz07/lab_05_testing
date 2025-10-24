package P3;
import java.util.Scanner;
public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Seleccione algun número:");
        System.out.println("1. Clases de Equivalencia");
        System.out.println("2. Casos de Prueba Válidos");
        System.out.println("3. Casos de Prueba No Válidos");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
        int opcion = sc.nextInt();
        System.out.println();
        switch (opcion) {
            case 1:
                System.out.println("** Clases de Equivalencia **\n");
                System.out.println("| Campo          | Condición de Entrada  | Clases Válidas           | Clases No Válidas      |");
                System.out.println("|--------------------------------------------------------------------------------------------|");
                System.out.println("| numeroPersonas | Rango: 1 a 15         | CEV1: 1–15               | CENV1: <1              |");
                System.out.println("| duracionHoras  | Rango: 1 a 8          | CEV2: 1–8                | CENV3: <1              |");
                System.out.println("| tipoUsuario    | Conjunto {'E','G'}    | CEV3: 'E' o 'G'          | CENV5: Otro carácter   |");
                break;

            case 2:
                System.out.println("** Casos de Prueba Válidos **\n");
                System.out.println("Caso | numeroPersonas | duracionHoras | tipoUsuario | Resultado Esperado | Descripción");
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("CPV1 | 5               | 2             | 'E'         | 20.0               | Empleado sin recargo");
                System.out.println("CPV2 | 15              | 3             | 'G'         | 18.0               | Gerente con recargo");
                System.out.println("CPV3 | 10              | 8             | 'E'         | 80.0               | Máximo sin recargo");
                break;

            case 3:
                System.out.println("** Casos de Prueba No Válidos **\n");
                System.out.println("Caso | numeroPersonas  | duracionHoras | tipoUsuario |    Resultado Esperado    | Clase Probada");
                System.out.println("----------------------------------------------------------------------------------------------- ");
                System.out.println("CPNV1| 0               | 2             | 'E'         | IllegalArgumentException | <1 personas");
                System.out.println("CPNV2| 20              | 3             | 'E'         | IllegalArgumentException | >15 personas");
                System.out.println("CPNV3| 5               | 0             | 'G'         | IllegalArgumentException | <1 horas");
                System.out.println("CPNV4| 5               | 12            | 'G'         | IllegalArgumentException | >8 horas");
                System.out.println("CPNV5| 5               | 2             | 'X'         | IllegalArgumentException | tipo inválido");
                break;

            case 0:
                System.out.println("** Programa finalizado **");
                break;

            default:
                System.out.println("** Opción inválida. Intente nuevamente **.");
        }

        sc.close();
    }


    public double calcularCostoReserva(int numeroPersonas, int duracionHoras, char tipoUsuario) {
        if (numeroPersonas < 1 || numeroPersonas > 15) {
            throw new IllegalArgumentException("Número de personas debe estar entre 1 y 15");
        }

        if (duracionHoras < 1 || duracionHoras > 8) {
            throw new IllegalArgumentException("Duración debe estar entre 1 y 8 horas");
        }

        if (tipoUsuario != 'E' && tipoUsuario != 'G') {
            throw new IllegalArgumentException("Tipo de usuario debe ser 'E' o 'G'");
        }

        double tarifaPorHora = 0;
        if (tipoUsuario == 'E') {
            tarifaPorHora = 10.0;  // Empleado
        } else {
            tarifaPorHora = 5.0;   // Gerente
        }

        double costoBase = tarifaPorHora * duracionHoras;

        if (numeroPersonas > 10) {
            costoBase = costoBase * 1.20;  // Recargo del 20%
        }

        return costoBase;
    }
}
