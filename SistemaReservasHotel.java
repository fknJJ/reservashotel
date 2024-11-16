import java.util.Scanner;

class ReservaNoDisponibleException extends Exception {
    public ReservaNoDisponibleException(String message) {
        super(message);
    }
}

public class SistemaReservasHotel {
    private static final int MAX_HABITACIONES = 25;
    private static final double COSTO_POR_NOCHE = 125.0;
    private static int habitacionesDisponibles = MAX_HABITACIONES;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Ingrese la cantidad de habitaciones que desea reservar: ");
            int habitaciones = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Ingrese la cantidad de noches de estancia: ");
            int noches = Integer.parseInt(scanner.nextLine());
            
            validarDatos(habitaciones, noches);
            realizarReserva(habitaciones, noches);
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ReservaNoDisponibleException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void validarDatos(int habitaciones, int noches) {
        if (habitaciones <= 0 || noches <= 0) {
            throw new IllegalArgumentException("La cantidad de habitaciones y noches debe ser mayor a cero.");
        }
    }

    private static void realizarReserva(int habitaciones, int noches) throws ReservaNoDisponibleException {
        if (habitaciones > habitacionesDisponibles) {
            throw new ReservaNoDisponibleException("No hay suficientes habitaciones disponibles.");
        }

        habitacionesDisponibles -= habitaciones;
        
        double costoTotal = habitaciones * noches * COSTO_POR_NOCHE;
        
        System.out.println("Reserva exitosa. Costo total: $" + costoTotal);
    }
}