package com.tz.test;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : TZ
 * 프로그램 :
 * 설    명 : 테스트
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
public interface Test {

    /**
     * <pre>
     * 시나리오 명 출력
     * </pre>
     *
     * @param 시나리오
     * @param 테스트 시나리오에서 사용될 argument
     */
    void scenario(String message, Object... args);

    /**
     * <pre>
     * 사전 조건 명기
     * </pre>
     *
     * @param 사전조건
     * @param 조건과 부합하는 argument
     */
    void given(String message, Object... args);

    /**
     * <pre>
     * 작동 조건 명기
     * </pre>
     *
     * @param 작동조건
     * @param 조건과 부합하는 argument
     */
    void when(String message, Object... args);

    /**
     * <pre>
     * 예상 결과 명기
     * </pre>
     *
     * @param 예상결과
     * @param 예상되는 결과값
     */
    void then(String message, Object... args);

    /**
     * <pre>
     * 추가 사항 명기
     * </pre>
     *
     * @param 추가사항
     * @param 조건과 부합하는 argument
     */
    void and(String message, Object... args);
}
