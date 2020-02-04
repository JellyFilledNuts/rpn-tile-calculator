package de.fhdw.wip.rpntilecalculator.view;

/**
 * Summary: Questionable for the type to condense information on the type
 * Author:  Khang Pham
 * Date:    2020/01/15
 */
interface TypeQuestionable {

    boolean isStack();

    boolean isOperand();

    boolean isAction();

    boolean isSetting();

    boolean isHistory();

}
