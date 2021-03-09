public class BigInt implements AstroInt{
    DoubleLinkedList<Integer> number = new DoubleLinkedList<Integer>();

    public BigInt(String input){
        number = toBigInt(input);
    }

    public DoubleLinkedList<Integer> toBigInt(String input){
        for(int i=0; i<input.length(); i++){
            char temp = input.charAt(i);
            number.add(Character.getNumericValue(temp));
        }
        return number;
    }

    @Override
    public AstroInt add(AstroInt x) {
        BigInt other = (BigInt)x;
        DoubleLinkedList<Integer> num1, num2, result;
        result=new DoubleLinkedList<>();
        int count, temp, resto, temp1, temp2, loops;

        num2=other.number;
        num1=this.number;

        temp1=num1.getNode(num1.size()-1).getElemento();
        temp2=num2.getNode(num2.size()-1).getElemento();

        if(num1.size()<=num2.size()) {
            loops = num2.size() + 1;
        }else{
            loops=num1.size()+1;
        }


        count=1;
        resto=0;
        while(count<=loops){
            temp=temp1+temp2+resto;
            if(temp>9){
                temp=temp%10;
                resto=1;
            }else{
                resto=0;
            }

            result.add(1, temp);

            count++;
            if(num1.getNode(num1.size()-count).getElemento()==null){
                temp1=0;
            }else{
                temp1=num1.getNode(num1.size()-count).getElemento();
            }

            if(num2.getNode(num2.size()-count).getElemento()==null){
                temp2=0;
            }else{
                temp2=num2.getNode(num2.size()-count).getElemento();
            }
        }
        if (result.getNode(0).getElemento()==0){
            result.remove(0);
        }
        this.number = result;
        return this;
    }

    @Override
    public AstroInt sub(AstroInt x) {
        /*
        BigInt other = (BigInt)x;
        DoubleLinkedList<Integer> num1, num2, result;
        result=new DoubleLinkedList<>();
        int count, temp, resto, temp1, temp2, loops;

        num2=other.number;
        num1=this.number;

        temp1=num1.getNode(num1.size()-1).getElemento();
        temp2=num2.getNode(num2.size()-1).getElemento();

        if(num1.size()<=num2.size()) {
            loops = num2.size() + 1;
        }else{
            loops=num1.size()+1;
        }


        count=1;
        resto=0;
        while(count<=loops){
            if(temp1==0 && count<num1.size() && count<=num2.size() || resto==1){
                temp1=10;
            }
            temp=temp1-temp2-resto;
            System.out.println("loop:" + temp + " temp1:" + temp1);
            if(temp<0){
                temp=(temp%10);
                resto=1;
            }else{
                resto=0;
            }

            result.add(1, temp);

            count++;
            if(num1.getNode(num1.size()-count).getElemento()==null){
                temp1=0;
            }else{
                temp1=num1.getNode(num1.size()-count).getElemento();
            }

            if(num2.getNode(num2.size()-count).getElemento()==null){
                temp2=0;
            }else{
                temp2=num2.getNode(num2.size()-count).getElemento();
            }
        }
        if (result.getNode(0).getElemento()==0){
            result.remove(0);
        }
        this.number = result;*/
        return null;
    }

    @Override
    public AstroInt mult(AstroInt x) {
        /*BigInt other = (BigInt)x;
        DoubleLinkedList<Integer> num1, num2;
        BigInt result=this;
        int temp1, temp2, multi, count;

        num2=other.number;
        num1=this.number;

        temp1=num1.getNode(num1.size()-1).getElemento();
        temp2=num2.getNode(num2.size()-1).getElemento();

        count=1;
        for(int i = num2.size(); i>0; i--){
            for(int j = num1.size(); j>0; j--) {
                multi = temp2*count;
                while (multi >= 0) {
                    this.add();

                    multi--;
                }

                temp1=num1.getNode(num1.size()-j).getElemento();
            }
            count=count*10;
            temp1=num2.getNode(num1.size()-i).getElemento();
        }

        this.number = result;*/
        return null;
    }

    @Override
    public AstroInt div(AstroInt x) {
        return null;
    }

    @Override
    public AstroInt mod(AstroInt x) {
        return null;
    }

    @Override
    public String toString(){
        return number.toString();
    }
}
