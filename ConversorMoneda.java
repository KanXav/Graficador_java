public class ConversorMoneda {
    private double valororiginal;


    public double calcularconversion(){
        if(valororiginal < 0){
            System.out.println("No hay un valor para convertir a dolares");
                return Double.NaN;
        }else{
            double conversion;
            conversion= (valororiginal*0.051);
            return conversion;
        }
    }
    
    
    
        public double getValororiginal() {
        return valororiginal;
    }

    public void setValororiginal(double valororiginal) {
        this.valororiginal = valororiginal;
    }
    
        
    @Override
    public String toString() {
        return "Tus " + valororiginal + " Pesos son:  " + calcularconversion() + " Dolares";
    }

   
    public ConversorMoneda(double valororiginal) {
        this.valororiginal = valororiginal;
    }
    
}
