posibles tads:

- BondiJet (aerolinea)
- Vuelo --> VueloPublico/VueloPrivado --> TipoVuelo(internacional/nacional)
- Cliente/Pasajero
- Boleto/Pasaje
- Asiento
- Aeropuerto

TAD BondiJet:
datos::
nombre: String
Cuit: String or Integer
registroClientes
registroAeropuertos

metodos::

      crearSistema(nombre, cuit) // Constructor, crea un nuevo sistema proporcionando un nombre y un CUIT
      registrarCliente(nombre, telefono, dni): void
      registrarAeropuerto(nombreAeropuerto, provincia/departamento/distrito/estado, direccion): void
      crearVueloPublicoNacional(destino, origen, horaSalida, horaLlegada): String(codigo de vuelo)
      crearVueloPublicoInternacional(destino, origen, horaSalida, horaLlegada, conEscala): String(codigo de vuelo)
      crearVueloPrivado(clienteRegistrado, listaPasajeros, destino, origen, horaSalida, horaLlegada): String(codigo de vuelo)
      consultarAsientosDisponibles(codigoVuelo): int[]
      venderPasajeVueloPublicoNacional(nombre, dni, codigoVuelo, numeroFila): void
      venderPasajeVueloPublicoInternacional(dni, codigoVuelo, numeroAsiento, seccion): void
      venderPasajeVueloPrivado(comprador, listaPasajerosRegistrados, codigoVuelo): void
      consultarVuelo(origen, destino, fecha ): listaCodigosVuelos (a 7 dias)
      cancelarPasaje(codVuelo, pasajero, asiento): void
      cancelarVuelo(codVuelo): void
      consultarRecaudacion(destino): double

IREP BONDIJET:

    - No se puede vender un pasaje si no esta registrado
    - no pueden registrarse 2 clientes con el mismo dni
    - No pueden haber 2 aeropuertos con la misma direccion/nombre

Irep Aeropuerto: - la direccion/nombre deben ser validos - No puede haber 2 codigos iguales de vuelos

Irep Vuelo: - Si un vuelo es privado, no puede ser internacional - un Vuelo publico nacional, no tiene escalas - No puede haber mas pasajeros que asientos - Vuelos internacionales deben tener 3 secciones - destinos nacionales e internacionales deben ser validos - la fecha de llegada debe ser posterior a la de salida

Irep Pasajero: - dni/telefono validos - lista de pasajes > 0

Irep Asiento: - filas > 0 - asientos debe ser A B C D E F

Tad Aeropuerto:

    datos::
        nombre: String
        estado: String
        direccion: String
        listaVuelosPublicos
        listaVuelosPrivados


    metodos::
        crearAeropuerto(nombreAeropuerto, estado, direccion) // Constructor
        buscarVueloPublico()
        buscarVueloPrivado()

TAD Vuelo (Clase abstracta o clase base/padre):
datos:
codigo: String o int
destino
origen
horaSalida
horaLlegada
listaPasajeros

metodos::
static obtenerDestino()
static obtenerOrigen()
static obtenerHoraSalida()
static obtenerHoraLLegada()

Tad VueloPublico:
datos::
listaAsientos[]: [[AsientoA, AsientoB....,], [AsientoA, AsientoB,....]]

    metodos::
        crearVueloPublicoNacional() ---> VueloNacional vueloNacional = new VueloNacional();
        crearVueloPublicoInternacional()
        devolverCodigoVuelo()
        hayAlgunAsientoDisponibleFila()

        consultarDisponibilidadDeAsientos(): Asiento[]

Tad VueloPrivado:
datos::
comprador
metodos::
crearVueloPublicoNacional()
consultarJetsNecesarios()
devolverCodigoVuelo()

Tad VueloIntenacional
datos::
listaDestinosValidos: ejemplo [brasil, china]
esConEscalas: boolean
escalas: lista[]
secciones: int
refrigerios: ???

    metodos::

Tad VueloNacional:
datos::
listaDestinosValidos: [la pampa, buenos aires ]
refrigerios: ???

    metodos::
        detalleVuelo() --> toString()

TAD Cliente/Pasajero:
datos::
nombre: string
telefono: double
dni: string
listaPasajes: []

metodos::
crearCliente(nombre, telefono, dni)
agregarPasaje()

Tad Asiento:
datos::
fila: int
asiento: char
disponible: boolean
metodos::
estaDisponible()
devolverCodigoCompleto()

TAD Pasaje: clase Abstracta
datos::
codigoVuelo: string
horaSalida: string
asiento: lista[tuplas<Character, int>]
nombreCliente:
destino:
precio:

    metodos::

Tad PasajeNacional:
datos::
tipo: privado o publico

Tad: PasajeInternacional:

    datos::
        seccion

VueloPublico y VueloPrivado, heredan de Vuelo

A tener en cuenta:

    Codigo modular -> pequenos componentes con funciones bien definidad y una responsabilidad.
    alta cohesion --> en POO, los metodos estan muy relacionados entre si, contenido claro y menos dependecias.
    Acoplamiento
    Flexibilidad
    Mantenible ->
    Escalabilidad: -> si se requiere agregar algo, dicha accion no provocara cambios significativos en el sistema
    a tener en cuenta a futuro: - Se pueden agregar mas categorias a los vuelos (como low cost, ejecutivo, etc)
                            -

public inteface Reparto {

    Reparto(String nombreReparto) // constructor - nueva instancia de Reparto
    /*
        Crea un deposito nuevo, el nombre es unico.
     * @return --> void
     * @throws RuntimeException si el nombre ya existe en algun registro (base de datos)
     *
     */
    void crearDeposito(String nombreDeposito) throw RuntimeException;
    /*
        carga un paquete en un deposito EXISTENTE, el nombre es unico.
     * @return --> void
     * @throws RuntimeException si el nombre del deposito ya existe en algun registro (base de datos) o si el
     * deposito no existe
     * @throws FormatException el codigo debe ser valido
     * @throws el destino debe existir
     */

    void cargarPaquete(String nombreDeposito, int codigo, String destino ) throw RuntimeException;
        /*
        quitar un paquete de un deposito EXISTENTE, el nombre es unico.
     * @return --> boolean
     * true: se encontro el paquete y se quito del deposito
     * false: no se encontro el paquete o no se encontro el deposito
     * @throws FormatException el codigo debe ser valido
     */
    boolean quitarPaquete ( int codigo )
          /*
        cargar en el despacho especial, solo puede ser usado una vez por dia?
        Los paquetes deben tener el mismo destino
     * @return --> boolean
     * true: Se cargo la lista completa de los paquetes
     * false: No se cargo la lista completa
     * @throws FormatException el codigo debe ser valido
     */
    boolean cargarDespacho ( int[] codigos[], String destino )

}
