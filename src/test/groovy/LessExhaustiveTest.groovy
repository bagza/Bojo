import spock.lang.Specification
import spock.lang.Unroll
import task1.ExhaustiveSolution
import task1.LessExhaustiveSolution

import static Utils.withBenchmark
import static task1.Task1Kt.generateAll
import static task1.Task1Kt.isCorrect

class LessExhaustiveTest extends Specification {

    @Unroll
    def "less exhaustive parenthesis of #n"(int n, List<String> answer) {
        given:

        def solution = new LessExhaustiveSolution()
        def result

        withBenchmark {
            result = solution.calculate(n)
        }

        expect:
        result.size() == answer.size()
        result.containsAll(answer)

        where:
        n  | answer
        1  | ["()"]
        3  | ["((()))",
              "(()())",
              "(())()",
              "()(())",
              "()()()"]
        5 | [""]
    }
}