package ex1;

public class LinkedStack<T> implements StackADT<T> {

    private LinearNode<T> top;
    private int count;

    public LinkedStack(){
        this.count = 0;
        this.top = null;
    }

    //adiciona
    @Override
    public void push(T element) {

        LinearNode <T> newNode = new LinearNode<>(element);

        if(this.count == 0){
            this.top = newNode;
        }else{
            newNode.setNext(this.top);
            this.top = newNode;
        }

        count++;

    }

    //tira
    @Override
    public T pop() throws EmptyCollectionException {

        if(isEmpty()){
            throw new EmptyCollectionException("vazio!!");
        }else{
            LinearNode <T> temp = this.top;
            this.top = this.top.getNext();
            temp.setNext(null);
            this.count--;
            return temp.getElement();
        }

    }

    //vê
    @Override
    public T peek() throws EmptyCollectionException {

        if(isEmpty()){
            throw new EmptyCollectionException("vazio!!");
        }

        return this.top.getElement();
    }

    //se tá cheio
    @Override
    public boolean isEmpty() {
        if(count==0){
            return true;
        }else{
            return false;
        }
    }

    //tamanho
    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        String s = "";
        int i = 0;

        LinearNode<T> node = top;
        while(node != null){
            s = node.toString() + "";
            node = node.getNext();
            i++;
        }

        return s;
    }
}

