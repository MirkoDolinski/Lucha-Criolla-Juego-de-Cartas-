public class Main {
    public static void main(String[] args) {
        VistaGrafica vista = new VistaGrafica();  
        Juego juego = new Juego(vista);  
        juego.iniciar();  
    }
}
