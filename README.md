# Laboratorio 05: Pruebas Unitarias
**Ingeniería de Software 2 - Semana 10**  
**Jefe de Prácticas: Sebastián Chávarry**

---

## OBJETIVO
Aplicar conceptos de pruebas unitarias utilizando JUnit 4, técnicas de caja blanca (camino básico) y caja negra (partición equivalente) para garantizar la calidad del software.

---

---

## EJERCICIO 1: PRUEBAS UNITARIAS CON JUNIT 4

### Descripción
Desarrollar pruebas unitarias completas para una clase `GestionBiblioteca` que implementa operaciones de gestión de una biblioteca.

### La clase contiene 5 funciones:

1. **`calcularPrecioConDescuento(double precioBase, double porcentajeDescuento)`**
   - Calcula el precio final aplicando un descuento
   - Lanza `IllegalArgumentException` si los valores son inválidos

2. **`estaDisponible(String titulo)`**
   - Verifica si un libro está disponible en la colección
   - Retorna `false` si el título es null o vacío

3. **`agregarLibro(String titulo)`**
   - Agrega un libro a la colección
   - Retorna `false` si el libro ya existe, es null, vacío o se alcanzó la capacidad máxima

4. **`obtenerCategoriaLector(int librosLeidos)`**
   - Clasifica lectores según cantidad de libros leídos
   - Categorías: "Principiante" (0), "Intermedio" (1-10), "Avanzado" (11-50), "Experto" (51+)

5. **`obtenerLibrosDisponibles()`**
   - Retorna una lista (copia) de todos los libros disponibles
   - Nunca retorna null

### Tareas a realizar:

#### 1. Crear la clase de pruebas `TestGestionBiblioteca.java`
Debe incluir:

> **Realizar al menos 1 test de cada función.**

**Tests para `calcularPrecioConDescuento`:**
- Precio sin descuento (0%)
- Precio con 50% de descuento
- Precio con 100% de descuento
- Excepción con precio negativo
- Excepción con descuento mayor a 100

**Tests para `estaDisponible`:**
- Libro no disponible (no agregado)
- Libro disponible después de agregarlo
- Libro null retorna false
- Cadena vacía retorna false

**Tests para `agregarLibro`:**
- Agregar libro exitosamente
- Agregar libro duplicado retorna false
- Agregar null retorna false
- Verificar trim de espacios

**Tests para `obtenerCategoriaLector`:**
- 0 libros → "Principiante"
- 5 libros → "Intermedio"
- 25 libros → "Avanzado"
- 100 libros → "Experto"
- Valores límite (1, 10, 11, 50, 51)
- Excepción con número negativo

**Tests para `obtenerLibrosDisponibles`:**
- Nunca retorna null
- Lista vacía inicialmente
- Contiene libros después de agregarlos
- Retorna una copia (modificaciones externas no afectan)


---

## EJERCICIO 2: PRUEBAS DE CAJA BLANCA - CAMINO BÁSICO

### Código a analizar:

```java
/**
 * Valida las credenciales de un usuario y determina su nivel de acceso
 * @param usuario Nombre de usuario
 * @param password Contraseña
 * @param intentos Número de intentos previos
 * @return Nivel de acceso: 0=bloqueado, 1=restringido, 2=normal, 3=admin
 */
public int validarAccesoUsuario(String usuario, String password, int intentos) {
    int nivelAcceso = 0;                                           
    
    if (intentos >= 3) {                                           
        System.out.println("Usuario bloqueado");
        return 0;                                                  
    }
    
    if (usuario == null || password == null) {                     
        return 0;                                                  
    }
    
    if (usuario.equals("admin") && password.equals("admin123")) {  
        nivelAcceso = 3;                                           
    } else if (password.length() >= 8) {                           
        nivelAcceso = 2;                                           
    } else {                                                       
        nivelAcceso = 1;                                           
    }
    
    return nivelAcceso;                                            
}
```

### Tareas a realizar:

#### 1. Dibujar el Grafo de Flujo de Control

#### 2. Calcular la Complejidad Ciclomática V(G)

#### 3. Identificar Caminos Independientes
Basándote en el valor de V(G), identifica ese número de caminos independientes.

**Formato esperado:**
```
Camino 1: 1 → 2 → 3
Camino 2: 1 → 2 → 4 → 5
Camino 3: ...
```

#### 4. Diseñar Casos de Prueba
Selecciona un camino independiente y diseña un caso de prueba.

**Formato de tabla:**

| Camino | usuario | password | intentos | Resultado Esperado | Descripción |
|--------|---------|----------|----------|-------------------|-------------|
| 1 | "test" | "test" | 3 | 0 | Usuario bloqueado por intentos |
| 2 | null | "test" | 0 | 0 | Usuario null |
| 3 | ... | ... | ... | ... | ... |

---

## EJERCICIO 3: PRUEBAS DE CAJA NEGRA - PARTICIÓN EQUIVALENTE

### Especificación del Sistema:

```java
/**
 * Sistema de reserva de salas de reunión
 * 
 * ENTRADAS:
 * - numeroPersonas: Entero entre 1 y 15 (capacidad de la sala)
 * - duracionHoras: Entero entre 1 y 8 (horas de reserva)
 * - tipoUsuario: Un carácter: 'E' (empleado), 'G' (gerente)
 * 
 * REGLAS DE NEGOCIO:
 * - Empleados: $10 por hora
 * - Gerentes: $5 por hora (50% descuento)
 * - Si numeroPersonas > 10, hay un recargo del 20%
 * 
 * SALIDAS:
 * - Retorna el costo de la reserva (double)
 * 
 * EXCEPCIONES:
 * - IllegalArgumentException si algún parámetro es inválido
 */
public double calcularCostoReserva(int numeroPersonas, int duracionHoras, char tipoUsuario) {
    // Validar numeroPersonas
    if (numeroPersonas < 1 || numeroPersonas > 15) {
        throw new IllegalArgumentException("Número de personas debe estar entre 1 y 15");
    }
    
    // Validar duracionHoras
    if (duracionHoras < 1 || duracionHoras > 8) {
        throw new IllegalArgumentException("Duración debe estar entre 1 y 8 horas");
    }
    
    // Validar tipoUsuario
    if (tipoUsuario != 'E' && tipoUsuario != 'G') {
        throw new IllegalArgumentException("Tipo de usuario debe ser 'E' o 'G'");
    }
    
    // Calcular tarifa base según tipo de usuario
    double tarifaPorHora = 0;
    if (tipoUsuario == 'E') {
        tarifaPorHora = 10.0;  // Empleado
    } else {
        tarifaPorHora = 5.0;   // Gerente
    }
    
    // Calcular costo base
    double costoBase = tarifaPorHora * duracionHoras;
    
    // Aplicar recargo si hay más de 10 personas
    if (numeroPersonas > 10) {
        costoBase = costoBase * 1.20;  // Recargo del 20%
    }
    
    return costoBase;
}
```

### Tareas a realizar:

#### 1. Crear Tabla de Clases de Equivalencia

Para cada campo de entrada, identifica:
- **Condición de entrada**: ¿Es un rango, valor específico, conjunto o condición lógica?
- **Clases válidas**: Valores que deberían ser aceptados
- **Clases no válidas**: Valores que deberían ser rechazados

**Plantilla:**

| Campo | Condición de Entrada | Clases Válidas | Clases No Válidas |
|-------|---------------------|----------------|-------------------|
| codigoSala | Valor específico (4 caracteres alfanuméricos) | CEV1: String de 4 caracteres alfanuméricos (ej: "S001", "SALA") | CENV1: String con menos de 4 caracteres (ej: "S1")<br>CENV2: String con más de 4 caracteres (ej: "SALA01")<br>CENV3: String con caracteres especiales (ej: "S@01")<br>CENV4: null |
| numeroPersonas | Rango (1-20) | CEV2: ... | CENV5: ...<br>CENV6: ... |
| duracionHoras | Rango (1-8) | CEV3: ... | CENV7: ...<br>CENV8: ... |
| tipoUsuario | Conjunto ('E', 'G', 'V') | CEV4: ... | CENV9: ... |

#### 2. Diseñar Casos de Prueba Válidos

Diseña casos que cubran todas las clases de equivalencia válidas.

**Plantilla:**

| Caso | codigoSala | numeroPersonas | duracionHoras | tipoUsuario | Resultado Esperado | Descripción |
|------|------------|----------------|---------------|-------------|-------------------|-------------|
| CPV1 | "S001" | 5 | 2 | 'E' | $20.0 | Empleado, sin recargo (5 ≤ 10), 10×2 |
| CPV2 | "SALA" | 15 | 3 | 'G' | $18.0 | Gerente, con recargo (15 > 10), 5×3×1.2 |
| CPV3 | ... | ... | ... | ... | ... | ... |

#### 3. Diseñar Casos de Prueba No Válidos

Diseña casos que cubran todas las clases de equivalencia no válidas.

**Plantilla:**

| Caso | codigoSala | numeroPersonas | duracionHoras | tipoUsuario | Resultado Esperado | Clase Probada |
|------|------------|----------------|---------------|-------------|-------------------|---------------|
| CPNV1 | "S1" | 5 | 2 | 'E' | IllegalArgumentException | CENV1 - Código muy corto |
| CPNV2 | "SALA01" | 5 | 2 | 'E' | IllegalArgumentException | CENV2 - Código muy largo |
| CPNV3 | ... | ... | ... | ... | ... | ... |

### Directrices importantes:

1. **Para rangos**: Define 1 clase válida y 2 no válidas (por debajo y por encima del rango)
2. **Para valores específicos**: Define 1 clase válida y 2 no válidas (menor y mayor)
3. **Para conjuntos**: Define 1 clase válida (todos los elementos) y 1 no válida (elementos fuera del conjunto)
4. **Para condiciones lógicas**: Define 1 clase válida y 1 no válida



---

## ENTREGABLES

### Formato de entrega: Repositorio de Github o Zip, donde se incluya:

1. **Proyecto Maven completo** con:
   - `GestionBiblioteca.java` (Ejercicio 1)
   - `TestGestionBiblioteca.java` (Ejercicio 1)
   - Ejecución exitosa de todos los tests

2. **Documento PDF o Word** con:
   - **Ejercicio 2**: 
     - Grafo de flujo dibujado
     - Cálculos de complejidad ciclomática
     - Lista de caminos independientes
     - Tabla de casos de prueba
   
   - **Ejercicio 3**:
     - Tabla de clases de equivalencia
     - Tabla de casos de prueba válidos
     - Tabla de casos de prueba no válidos



