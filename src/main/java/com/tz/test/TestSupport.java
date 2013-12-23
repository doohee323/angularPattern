package com.tz.test;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : TZ
 * 프로그램 :
 * 설    명 : 테스트케이스 작성을 위해 확장하여 사용한다.
 * 작 성 자 : TZ
 * 작성일자 : 2013-02-01
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2013-02-01             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
public abstract class TestSupport implements Test {
    private static final String LOG_NAME = "test";

    private static boolean inited = false;

    private Logger LOG = LoggerFactory.getLogger(LOG_NAME);

    private enum Stage {
        SCENARIO, GIVEN, WHEN, THEN
    };

    Stage stage = null;

    public void init() {
        if(!inited) {
            printFixtureInformation();
            inited = true;
        }
    }

    /**
     * <pre>
     * 시나리오 명 출력
     * </pre>
     *
     * @param 시나리오
     * @param 테스트 시나리오에서 사용될 argument
     */
    public void scenario(String message, Object... args) {
        if (isCurrent(Stage.GIVEN))
            and(message, args);
        else
            println("Scenario: " + message, args);
        this.stage = Stage.SCENARIO;
    }

    /**
     * <pre>
     * 사전 조건 명기
     * </pre>
     *
     * @param 사전조건
     * @param 조건과 부합하는 argument
     */
    public void given(String message, Object... args) {

        init();
        checkFirstCallAndUseTestCaseMethodNameAsScenarioMessage();

        if (isCurrent(Stage.GIVEN))
            and(message, args);
        else
            println("\tGiven: " + message, args);
        this.stage = Stage.GIVEN;
    }

    /**
     * <pre>
     * 작동 조건 명기
     * </pre>
     *
     * @param 작동조건
     * @param 조건과 부합하는 argument
     */
    public void when(String message, Object... args) {

        init();
        checkFirstCallAndUseTestCaseMethodNameAsScenarioMessage();

        if (isCurrent(Stage.WHEN))
            and(message, args);
        else
            println("\tWhen: " + message, args);
        this.stage = Stage.WHEN;
    }

    /**
     * <pre>
     * 예상 결과 명기
     * </pre>
     *
     * @param 예상결과
     * @param 예상되는 결과값
     */
    public void then(String message, Object... args) {
        if (isCurrent(Stage.THEN))
            and(message, args);
        else {

            if (!isCurrent(Stage.WHEN))
                throw new IllegalStateException("You have to call when() first");

            println("\tThen: " + message, args);
        }
        this.stage = Stage.THEN;
    }

    /**
     * <pre>
     * 추가 사항 명기
     * </pre>
     *
     * @param 추가사항
     * @param 조건과 부합하는 argument
     */
    public void and(String message, Object... args) {
        println("\t\tAnd: " + String.format(message, args));
    }

    private void printFixtureInformation() {
        Method testCaseMethod = getTestCaseMethod();
        if(testCaseMethod == null)
            return;
        Class<?> clazz = testCaseMethod.getDeclaringClass();
        println("[Start scenario set : %s]", clazz.getSimpleName());
        println("\ttime: %tc", System.currentTimeMillis());
    }

    private void checkFirstCallAndUseTestCaseMethodNameAsScenarioMessage() {
        if (isNotFirstCall())
            return;

        String testCaseMethodName = getTestCaseMethodName();
        if (testCaseMethodName != null)
            scenario(getTestCaseMethodName());
    }

    /**
     * <pre>
     * 현재 진행 단계 확인
     * </pre>
     *
     * @param expect
     *            예상한 진행 단계
     * @return 지금 진행 단계가 예상한 진행 단계면 true, 아니면 false
     */
    private boolean isCurrent(Stage expect) {
        return (this.stage != null && this.stage == expect);
    }

    /**
     * <pre>
     * 이미 scenario()를 호출한 상황인지 아닌지 확인
     * </pre>
     *
     * @return scenario()나 다른 given, when, then 중 하나를 호출한 이 후라면 true, 아니면 false
     */
    private boolean isNotFirstCall() {
        return stage != null;
    }

    /**
     * <pre>
     * 테스트 케이스 메서드 이름을 찾아서 반환 scenario()를 호출하지 않고 given(), when()을 호출했을 경우 이
     * 메서드를 호출해 테스트 케이스 메서드 이름을 찾아서 시나리오 명으로 출력한다.
     * </pre>
     *
     * @return 테스트 케이스 메서드 이름, 또는 null
     */
    private String getTestCaseMethodName() {

        Method method = getTestCaseMethod();
        return method == null ? null : method.getName();
    }

    private Method getTestCaseMethod() {
        Method result = null;
        StackTraceElement ste[] = Thread.currentThread().getStackTrace();

        int max = ste.length;
        for (int index = 1; index < max; index++) {
            StackTraceElement e = ste[index];

            try {
                Method method = ReflectionUtils.findMethod(
                        Class.forName(e.getClassName()), e.getMethodName());
//                if (method != null && method.isAnnotationPresent(org.junit.Test.class)) {
//                    result = method;
//                    break;
//                }
            } catch (ClassNotFoundException e1) {
                continue;
            }
        }
        return result;
    }

    private void println(String message, Object... args) {
        LOG.info(String.format(message, args));
    }
}