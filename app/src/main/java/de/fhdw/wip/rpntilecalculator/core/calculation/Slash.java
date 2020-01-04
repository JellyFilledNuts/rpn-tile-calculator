package de.fhdw.wip.rpntilecalculator.core.calculation;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OMatrix;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OPolynom;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OSet;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OTuple;
import de.fhdw.wip.rpntilecalculator.core.model.operand.Operand;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Slash extends Action {

    @NotNull private static final Times TIMES = Times.getInstance();

    @NotNull private static final Slash SLASH = new Slash();

    @Contract(pure = true) @NotNull public static Slash getInstance() { return SLASH; }
    private Slash() { }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    //region Double
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble oDouble1, @NotNull ODouble oDouble2) {
        return new ODouble(oDouble1.getDouble() / oDouble2.getDouble());
    }

    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble oDouble, @NotNull OFraction oFraction) {
        return new ODouble(oDouble.getDouble() / oFraction.getDouble());
    }

    //endregion

    //region Fraction
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OFraction on(@NotNull OFraction oFraction1, @NotNull OFraction oFraction2) {
        return new OFraction(oFraction1.getFraction().divide(oFraction2.getFraction()));
    }

    @Contract(pure = true) @NotNull ODouble on(@NotNull OFraction oFraction, @NotNull ODouble oDouble) {
        return new ODouble(oFraction.getDouble() / oDouble.getDouble());
    }

    //endregion

    //region Set
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OSet on(@NotNull OSet oSet, @NotNull ODouble oDouble) {
        return TIMES.on(oDouble.turnAroundSign(), oSet);
    }

    @Contract(pure = true) @NotNull OSet on(@NotNull OSet oSet, @NotNull OFraction oFraction) {
        return on(oSet, new ODouble(oFraction.getDouble()));
    }

    //endregion

    //region Matrix
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix oMatrix, @NotNull ODouble oDouble) {
        return TIMES.on(oDouble.inverseValue(), oMatrix);
    }

    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix oMatrix, @NotNull OFraction oFraction) {
        return on(oMatrix, new ODouble(oFraction.getDouble()));
    }

    //endregion

    //region Polynom
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OPolynom on(@NotNull OPolynom oPolynom1, @NotNull OPolynom oPolynom2) {
        return new OPolynom(oPolynom1.getPolynom().multiply(oPolynom2.inverseValue().getPolynom()));
    }

    @Contract(pure = true) @NotNull OPolynom on(@NotNull OPolynom oPolynom, @NotNull ODouble oDouble) {
        return TIMES.on(oDouble.inverseValue(), oPolynom);
    }

    @Contract(pure = true) @NotNull OPolynom on(@NotNull OPolynom oPolynom, @NotNull OFraction oFraction) {
        return on(oPolynom, new ODouble(oFraction.getDouble()));
    }

    //endregion

    //region Tuple
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OTuple on(@NotNull OTuple oTuple1, @NotNull OTuple oTuple2) {
        return TIMES.on(oTuple1, oTuple2.inverseValue());
    }

    @Contract(pure = true) @NotNull OTuple on(@NotNull OTuple oTuple, @NotNull ODouble oDouble) {
        return TIMES.on(oDouble.inverseValue(), oTuple);
    }

    @Contract(pure = true) @NotNull OTuple on(@NotNull OTuple oTuple, @NotNull OFraction oFraction) {
        return TIMES.on(oFraction.inverseValue(), oTuple);
    }

    //endregion

}
