package jga.example.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        if(args.length == 0) {
            System.err.printf("usage: %s <JSON file>", Main.class.getName());
            System.err.println();
            return;
        }
        // create a CharStream that reads from standard input
        String fileName = args[0];
        CharStream cs = CharStreams.fromFileName(fileName);
        parseAndPrint(cs);
    }

    public static void parseAndPrint(CharStream cs) {
        JSONParser parser = getParser(cs); // begin parsing at init rule
        ParseTree tree = parser.json(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
    }

    public static JSONParser getParser(CharStream cs)  {
        JSONLexer lexer = new JSONLexer(cs);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        return new JSONParser(tokens);
    }
}
