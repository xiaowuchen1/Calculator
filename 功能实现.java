

    private void calculate(){
        String b = operatorStack.pop();
        double c = operandStack.pop();
        double d = operandStack.pop();
        double e;
        if (b.equals("+")) {
            e = d + c;
            operandStack.push(e);
        }
        if (b.equals("-")) {
            e = d - c;
            operandStack.push(e);
        }
        if (b.equals("*")) {
            e = d * c;
            operandStack.push(e);
        }
        if (b.equals("/")) {
            if(c==0)
                throw new ArithmeticException("DivideByZero!");//不可修改为Exception
            // Exception的异常是必须处理的,是受控异常;而ArithmeticException 不是必须处理的 ,受控异常必须强制处理
            e = d / c;
            operandStack.push(e);
        }
    }

    private Double calculate(String text){
        HashMap<String,Integer> precede=new HashMap<>();
        precede.put("(",0);
        precede.put(")",0);
        precede.put("/",2);
        precede.put("*",2);
        precede.put("-",1);
        precede.put("+",1);
        precede.put("#",0);

        operatorStack.push("#");

        int flag=0;
        for(int i=0;i<text.length();i++){
            String a=String.valueOf(text.charAt(i));
            if(!a.matches("[0-9.]")){
                if(flag!=i)
                    operandStack.push(Double.parseDouble(text.substring(flag,i)));
                flag=i+1;
                while(!(a.equals("#")&&operatorStack.peek().equals("#"))){
                    if(precede.get(a)>precede.get(operatorStack.peek())||a.equals("(")){
                        operatorStack.push(a);
                        break;
                    }else {
                        if(a.equals(")")) {
                            while(!operatorStack.peek().equals("("))
                                calculate();
                            operatorStack.pop();
                            break;
                        }
                        calculate();
                    }
                }

            }
        }

        return(operandStack.pop());
    }


    public static void main(String[] args){
        new jsq();
    }
}