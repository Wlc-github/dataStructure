package listlink;


import java.util.Stack;

/**
 * 简单中缀转后缀
 * 只涉及算法没有进行分割单词
 */
public class ReversePolishDemo {
    public static void main(String[] args) {
        String infix = "5/3+4*(3-4) ";//中缀
        ReversePolish reversePolish=new ReversePolish();
        System.out.println(reversePolish.transform(infix));
    }


}
class ReversePolish{
    Stack<Character> stack=new Stack<>();//符号栈
    int index=0;
    char ch;
    char cur;
    StringBuffer stringBuffer=new StringBuffer();//中间串
    StringBuffer end=new StringBuffer();//结果串
    public StringBuffer transform(String s){
        while(index<s.length()){
                ch=s.substring(index,index+1).charAt(0);
                if(ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='('||ch==')'){
                    if(ch=='('){
                        stack.push(ch);
                    }
                    else if(ch==')'){
                        while(stack.peek()!='('){
                            end.append(stack.pop());
                        }
                        stack.pop();
                    }
                    else {
                        if (stack.isEmpty()) {
                            stack.push(ch);
                        } else {
                            if(stack.peek()=='('){
                                stack.push(ch);
                            }
                            else {
                                if (judge(ch, stack.peek())) {
                                    stack.push(ch);
                                } else {
                                    end.append(stack.pop());
                                    stack.push(ch);
                                }
                            }
                        }
                    }
                }
                else{//对非运算符进行处理
                    if(ch!=' ')end.append(ch);
                }
                index++;
        }
        while (!stack.isEmpty()) {
            end.append(stack.pop());
        }
        return end;
    }
    public boolean judge(Character character,Character stackTop){//返回true表示当前符号优先级更高
        return (character=='*'||character=='/')&&(stackTop=='-'||stackTop=='+');
    }
}