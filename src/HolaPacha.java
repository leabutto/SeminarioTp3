import java.util.*;

public class HolaPacha {
    private static final List<Administrador> administradores = new ArrayList<>();
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Producto> productos = new ArrayList<>();
    private static final List<Inventario> inventarios = new ArrayList<>();
    private static final List<Venta> ventas = new ArrayList<>();
    private static final List<Proveedor> proveedores = new ArrayList<>();

    private static List<AlertaStock> alertas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("=== MENÚ DE GESTION ===");
            System.out.println("________________________________");
            System.out.println("1. Gestionar Usuarios");
            System.out.println("2. Gestionar Productos");
            System.out.println("3. Gestionar Inventarios");
            System.out.println("4. Gestionar Ventas");
            System.out.println("5. Gestionar Proveedores");
            System.out.println("6. Gestionar Alertas de Stock");
            System.out.println("7. Gestionar Facturas");
            System.out.println("0. Salir");
            System.out.println("________________________________");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> gestionarUsuarios(scanner);
                case 2 -> gestionarProductos(scanner);
                case 3 -> gestionarInventarios(scanner);
                case 4 -> gestionarVentas(scanner);
                case 5 -> gestionarProveedores(scanner);
                case 6 -> gestionarAlertasStock(scanner);
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);

        scanner.close();
    }
    private static void gestionarAlertasStock(Scanner scanner) {
        int opcion;
        do {
            System.out.println("=== Gestión de Alertas de Stock ===");
            System.out.println("1. Crear Alerta de Stock Mínimo");
            System.out.println("2. Listar Alertas de Stock");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearAlertaStock(scanner);
                    break;
                case 2:
                    listarAlertasStock();
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 0);
    }

    private static void crearAlertaStock(Scanner scanner) {
        System.out.print("Ingrese el ID del producto para la alerta de stock: ");
        int idProducto = scanner.nextInt();
        System.out.print("Ingrese la cantidad mínima de stock para la alerta: ");
        int cantidadMinima = scanner.nextInt();

        Producto producto = productos.stream().filter(p -> p.getIdProducto() == idProducto).findFirst().orElse(null);
        if (producto != null) {
            AlertaStock alerta = new AlertaStock(alertas.size() + 1, producto, cantidadMinima);
            alertas.add(alerta);
            System.out.println("Alerta de stock creada correctamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void listarAlertasStock() {
        System.out.println("=== Lista de Alertas de Stock ===");
        for (AlertaStock alerta : alertas) {
            Producto producto = alerta.getProducto();
            if (producto.getCantidad() < alerta.getCantidadMinima()) {
                System.out.println("Alerta ID: " + alerta.getIdAlerta() +
                        ", Producto: " + producto.getNombre() +
                        ", Stock actual: " + producto.getCantidad() +
                        ", Stock mínimo requerido: " + alerta.getCantidadMinima());
            }
        }
    }
    // Métodos para gestionar usuarios
    private static void gestionarUsuarios(Scanner scanner) {
        int opcion;
        do {
            System.out.println("=== Gestión de Usuarios ===");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> agregarUsuario(scanner);
                case 2 -> listarUsuarios();
                case 0 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void agregarUsuario(Scanner scanner) {
        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = scanner.next();
        System.out.print("Ingrese el email del usuario: ");
        String email = scanner.next();
        System.out.print("Ingrese la contraseña del usuario: ");
        String password = scanner.next();

        Usuario usuario = new Usuario(usuarios.size() + 1, nombre, email, password);
        usuarios.add(usuario);
        System.out.println("Usuario agregado correctamente.");
    }

    private static void listarUsuarios() {
        System.out.println("=== Lista de Usuarios ===");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    // Métodos para gestionar productos
    private static void gestionarProductos(Scanner scanner) {
        int opcion;
        do {
            System.out.println("=== Gestión de Productos ===");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Listar Productos");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> agregarProducto(scanner);
                case 2 -> listarProductos();
                case 0 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void agregarProducto(Scanner scanner) {
        scanner.nextLine();  // Limpiar el buffer para evitar conflictos
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la descripción del producto: ");
        String descripcion = scanner.nextLine();

        int cantidad = 0;
        while (true) {
            try {
                System.out.print("Ingrese la cantidad en stock: ");
                cantidad = scanner.nextInt();
                if (cantidad < 0) {
                    System.out.println("La cantidad no puede ser negativa. Intente nuevamente.");
                    continue;
                }
                break;  // Salir del bucle si la entrada es válida
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero para la cantidad.");
                scanner.next();  // Limpiar el buffer para permitir otra entrada
            }
        }

        float precioCompra = 0;
        while (true) {
            try {
                System.out.print("Ingrese el precio de compra: ");
                precioCompra = scanner.nextFloat();
                if (precioCompra < 0) {
                    System.out.println("El precio no puede ser negativo. Intente nuevamente.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número decimal para el precio de compra.");
                scanner.next();  // Limpiar el buffer para permitir otra entrada
            }
        }

        float precioVenta = 0;
        while (true) {
            try {
                System.out.print("Ingrese el precio de venta: ");
                precioVenta = scanner.nextFloat();
                if (precioVenta < 0) {
                    System.out.println("El precio no puede ser negativo. Intente nuevamente.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número decimal para el precio de venta.");
                scanner.next();  // Limpiar el buffer para permitir otra entrada
            }
        }

        // Crear el objeto Producto y agregarlo a la lista
        Producto producto = new Producto(productos.size() + 1, nombre, descripcion, cantidad, precioCompra, precioVenta, new Date());
        productos.add(producto);
        System.out.println("Producto agregado correctamente.");
    }

    private static void listarProductos() {
        System.out.println("=== Lista de Productos ===");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    // Métodos para gestionar inventarios
    private static void gestionarInventarios(Scanner scanner) {
        int opcion;
        do {
            System.out.println("=== Gestión de Inventarios ===");
            System.out.println("1. Agregar al Inventario");
            System.out.println("2. Listar Inventario");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> agregarInventario(scanner);
                case 2 -> listarInventarios();
                case 0 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void agregarInventario(Scanner scanner) {
        System.out.print("Ingrese el ID del producto: ");
        int idProducto = scanner.nextInt();
        System.out.print("Ingrese la cantidad a agregar: ");
        int cantidad = scanner.nextInt();

        Producto producto = productos.stream().filter(p -> p.getIdProducto() == idProducto).findFirst().orElse(null);
        if (producto != null) {
            Inventario inventario = new Inventario(inventarios.size() + 1, producto, cantidad);
            inventarios.add(inventario);
            System.out.println("Inventario actualizado correctamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void listarInventarios() {
        System.out.println("=== Lista de Inventario ===");
        for (Inventario inventario : inventarios) {
            System.out.println(inventario);
        }
    }

    // Métodos para gestionar ventas
    private static void gestionarVentas(Scanner scanner) {
        int opcion;
        do {
            System.out.println("=== Gestión de Ventas ===");
            System.out.println("1. Registrar Venta");
            System.out.println("2. Listar Ventas");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> registrarVenta(scanner);
                case 2 -> listarVentas();
                case 0 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void registrarVenta(Scanner scanner) {
        System.out.print("Ingrese el ID del producto: ");
        int idProducto = scanner.nextInt();
        System.out.print("Ingrese la cantidad vendida: ");
        int cantidad = scanner.nextInt();

        Producto producto = productos.stream().filter(p -> p.getIdProducto() == idProducto).findFirst().orElse(null);
        if (producto != null && producto.getCantidad() >= cantidad) {
            Venta venta = new Venta(ventas.size() + 1, new Date(), producto.getPrecioVenta() * cantidad);
            ventas.add(venta);
            producto.setCantidad(producto.getCantidad() - cantidad);
            System.out.println("Venta registrada correctamente.");
        } else {
            System.out.println("Producto no encontrado o cantidad insuficiente.");
        }
    }

    private static void listarVentas() {
        System.out.println("=== Lista de Ventas ===");
        for (Venta venta : ventas) {
            System.out.println(venta);
        }
    }

    // Métodos para gestionar proveedores
    private static void gestionarProveedores(Scanner scanner) {
        int opcion;
        do {
            System.out.println("=== Gestión de Proveedores ===");
            System.out.println("1. Agregar Proveedor");
            System.out.println("2. Listar Proveedores");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> agregarProveedor(scanner);
                case 2 -> listarProveedores();
                case 0 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void agregarProveedor(Scanner scanner) {
        System.out.print("Ingrese el nombre del proveedor: ");
        String nombre = scanner.next();
        System.out.print("Ingrese el contacto del proveedor: ");
        String contacto = scanner.next();

        Proveedor proveedor = new Proveedor(proveedores.size() + 1, nombre, contacto);
        proveedores.add(proveedor);
        System.out.println("Proveedor agregado correctamente.");
    }

    private static void listarProveedores() {
        System.out.println("=== Lista de Proveedores ===");
        for (Proveedor proveedor : proveedores) {
            System.out.println(proveedor);
        }
    }
}
