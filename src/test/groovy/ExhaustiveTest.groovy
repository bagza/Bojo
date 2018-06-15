import spock.lang.*
import task1.ExhaustiveSolution

import static Utils.withBenchmark
import static task1.Task1Kt.isCorrect
import static task1.Task1Kt.generateAll

class ExhaustiveTest extends Specification {

    @Unroll
    def "exhaustive parenthesis of #n"(int n, List<String> answer) {
        given:

        def solution = new ExhaustiveSolution()
        Iterable<String> result

        withBenchmark {
            result = solution.calculate(n)
        }

        expect:
        result.size() == answer.size()
        //Thank you groovy!
        result.containsAll()

        where:
        n | answer
        1 | ["()"]
        3 | ["((()))",
             "(()())",
             "(())()",
             "()(())",
             "()()()"]
        5 | [""]
    }


    @Unroll
    def "correctness of #sample"(String sample, Boolean expected) {
        expect:
        isCorrect(sample) == expected

        where:
        sample               | expected
        "()()()"             | true
        "()(((((((((((())))" | false
        "(((((("             | false
    }


    @Unroll
    def "generation of #length"(int length, int expectedNumber) {
        given:
        def list = generateAll(length)
        println(list)

        expect:
        list.size() == expectedNumber

        where:
        length | expectedNumber
        2      | 4
        3      | 8
        4      | 16
        16     | 65536
    }
}