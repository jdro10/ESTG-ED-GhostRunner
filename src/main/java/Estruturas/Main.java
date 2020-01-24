package ex1;

public class Main {

    public static void main(String[] args){

        /**
         *     Carro c = new Carro("bmw");
         *     Carro joao = new Carro("m4");
         *     Carro jorge = new Carro("clio");
         *
         *     ArrayStack <Carro> as= new ArrayStack<>();
         *
         *     as.push(c);
         *     as.push(joao);
         *     System.out.println(as.toString());
         *     as.push(jorge);
         *     System.out.println(as.toString());
         *     try{
         *         as.pop();
         *     }catch (EmptyCollectionException ex){
         *             System.out.println(ex);
         *     }
         *     System.out.println(as.toString());
         */

        Carro Joao = new Carro("320d");
        Carro Jorge = new Carro("clio");
        Carro Hugo = new Carro("118d");

        ArrayStack <Carro> as = new ArrayStack<>();
        as.push(Joao);
        as.push(Jorge);
        System.out.println(as.toString());
        as.push(Hugo);
        System.out.println(as.toString());
        try{
            System.out.println(as.peek());
        }catch (EmptyCollectionException ex){
            System.out.println(ex);
        }


    }
}
