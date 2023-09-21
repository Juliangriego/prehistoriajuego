package Clases;

public class Celdas {
    int habitantes;
    double agua_suministro,agua_produccion,
        comida_suministro,comida_produccion,
        difusion_cultural, difusion_tecnologica,
        intercambio_economico, intercambio_social;
    double sociabilidad = 2;
    String  dif_cul_1, dif_cul_2, dif_cul_3, dif_tec_1, dif_tec_2, dif_tec_3;
    boolean suministro_neto_comida, suministro_neto_agua;
    double agua_acumulada,comida_acumulada;

    //Constructores
    Celdas(int h, double agua_sum,double agua_prod, double comida_prod, double comida_sum, double difusion_cult,
           double difusion_tec, double intercambio_econ, double intercambio_soc, double sociab){
        this.habitantes=h;
        this.agua_produccion=agua_prod;
        this.agua_suministro=agua_sum;
        this.comida_produccion=comida_prod;
        this.comida_suministro=comida_sum;
        this.difusion_cultural=difusion_cult;
        this.difusion_tecnologica = difusion_tec;
        this.intercambio_economico=intercambio_econ;
        this.intercambio_social=intercambio_soc;
        this.sociabilidad=sociab;
    }
    //Getters

    public double getAgua_suministro() {return agua_suministro;}
    public double getAgua_produccion() {return agua_produccion;}
    public double getComida_produccion() {return comida_produccion;}
    public double getComida_suministro() {return comida_suministro;}
    public double getDifusion_cultural() {return difusion_cultural;}
    public double getDifusion_tecnologica() {return difusion_tecnologica;}
    public int getHabitantes() {return habitantes;}
    public double getIntercambio_economico() {return intercambio_economico;}
    public double getIntercambio_social() {return intercambio_social;}
    public double getSociabilidad() {return sociabilidad;}
    public double getAgua_acumulada() {return agua_acumulada;}
    public double getComida_acumulada() {return comida_acumulada;}

    //Setters
    public void setAgua_produccion(double agua_produccion) {this.agua_produccion = agua_produccion;}
    public void setAgua_suministro(double agua_suministro) {this.agua_suministro = agua_suministro;}
    public void setComida_produccion(double comida_produccion) {this.comida_produccion = comida_produccion;}
    public void setComida_suministro(double comida_suministro) {this.comida_suministro = comida_suministro;}
    public void setDifusion_cultural(double difusion_cultural) {this.difusion_cultural = difusion_cultural;}
    public void setDifusion_tecnologica(double difusion_tecnologica) {this.difusion_tecnologica = difusion_tecnologica;}
    public void setHabitantes(int habitantes) {this.habitantes = habitantes;}
    public void setIntercambio_economico(double intercambio_economico) {this.intercambio_economico = intercambio_economico;}
    public void setIntercambio_social(double intercambio_social) {this.intercambio_social = intercambio_social;}
    public void setSociabilidad(double sociabilidad) {this.sociabilidad = sociabilidad;}
    public void setAgua_acumulada(double agua_acumulada) {this.agua_acumulada = agua_acumulada;}
    public void setComida_acumulada(double comida_acumulada) {this.comida_acumulada = comida_acumulada;}


    //Metodos
    /*Cada turno se actualizan los valores. La población va a crecer a medida que se acumule una cierta cantidad de
    * comida y agua, y una vez que crece cada habitante consume también comida y agua. En principio pongamos que sólo
    * 5 habitantes pueden conseguir 2 recursos de agua y otros 5 2 recursos de comida */

    void turno() {
        /*
        Primero hacemos que la población produzca y luego consuma agua y comida.
        En principio, la sociabilidad va a ser una medida de eficiencia de la extracción de recursos
         */

        suministro_agua_comida();

        suministro_neto_agua = getAgua_suministro() > getAgua_produccion();
        suministro_neto_comida = getComida_suministro() > getComida_produccion();
        /* Primero me fijo si es verdad que hubo superavit de comida y agua, y hago condicionales
        En caso que sea verdadero, acumulo el agua o comida. En caso que sea falso, hago que retroceda de a 1 unidad
        para darle tiempo al jugador de hacer modificaciones.
         */
        if (suministro_neto_agua) {
            setAgua_acumulada(getAgua_produccion() - getAgua_suministro());
        } else {
            setAgua_acumulada(getAgua_acumulada() - 1);
        }
        if (suministro_neto_comida) {
            setComida_acumulada(getComida_produccion() - getComida_suministro());
        } else {
            setComida_acumulada(getComida_acumulada() - 1);
        }

        /*
        Si la celda acumula 10 o más de comida y 10 o más de agua, aumenta la población
        y reinicia el acumulador. Si hay un acumulado de 8 de comida y un superavit de 4,
        en el turno acumularía 12. Aumenta la población pero aun quedan 2 de comida
        restante.
         */
        if((agua_acumulada>=10)&&(comida_acumulada>=10)){
            aumenta_poblacion();
            setComida_acumulada(getComida_acumulada()-10);
            setAgua_acumulada(getAgua_acumulada()-10);
        }
    }

    void aumenta_poblacion(){
        setHabitantes(getHabitantes() + 1);
    }

    void suministro_agua_comida(){
        if (getHabitantes()<=10){
            setAgua_suministro(getSociabilidad()*getHabitantes()/2);
            setComida_suministro(getSociabilidad()*getHabitantes()/2);
        } else {
            setAgua_suministro(getSociabilidad()*2.5);
            setComida_suministro(getSociabilidad()*2.5);
        }
    }
}