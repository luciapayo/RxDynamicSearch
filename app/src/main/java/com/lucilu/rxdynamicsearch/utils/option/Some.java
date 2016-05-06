package com.lucilu.rxdynamicsearch.utils.option;

import com.lucilu.rxdynamicsearch.utils.rx.Unit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.FuncN;

/**
 * Option with a value.
 *
 * @param <T> type of the existing object
 */
public class Some<T> extends Option<T> {

    @NonNull
    private final T mValue;

    Some(@NonNull final T value) {
        mValue = value;
    }

    @Override
    public boolean isSome() {
        return true;
    }

    @Override
    public Option<T> ifSome(@NonNull final Action1<T> action) {
        action.call(mValue);
        return this;
    }

    @Override
    public Option<T> ifNone(@NonNull final Action0 action) {
        // Do nothing
        return this;
    }

    @NonNull
    @Override
    public <OUT> Option<OUT> map(@NonNull final Func1<T, OUT> f) {
        return Option.ofObj(f.call(mValue));
    }

    @NonNull
    @Override
    public <OUT> Option<OUT> flatMap(@NonNull final Func1<T, Option<OUT>> f) {
        return f.call(mValue);
    }

    @NonNull
    @Override
    public Option<T> filter(@NonNull final Func1<T, Boolean> predicate) {
        return predicate.call(mValue) ? this : NONE;
    }

    @NonNull
    @Override
    public Option<T> orOption(@NonNull final Func0<Option<T>> f) {
        return this;
    }

    @NonNull
    @Override
    public T orDefault(@NonNull final Func0<T> def) {
        return mValue;
    }

    @NonNull
    @Override
    T getUnsafe() {
        return mValue;
    }

    @NonNull
    @Override
    public <OUT> Option<OUT> ofType(@NonNull final Class<OUT> type) {
        return type.isInstance(mValue) ? Option.ofObj(type.cast(mValue)) : Option.NONE;
    }

    @NonNull
    @Override
    public <OUT> OUT match(@NonNull final Func1<T, OUT> fSome,
                           @NonNull final Func0<OUT> fNone) {
        return fSome.call(mValue);
    }

    @NonNull
    @Override
    public Unit matchAction(@NonNull final Action1<T> fSome,
                            @NonNull final Action0 fNone) {
        return Unit.from(() -> fSome.call(mValue));
    }

    @Nullable
    @Override
    public <OUT> OUT matchUnsafe(@NonNull Func1<T, OUT> fSome, @NonNull Func0<OUT> fNone) {
        return fSome.call(mValue);
    }

    @NonNull
    @Override
    public <IN, OUT2> Option<OUT2> lift(@NonNull final Option<IN> option,
                                        @NonNull final Func2<T, IN, OUT2> f) {
        return option.map(b -> f.call(mValue, b));
    }

    @NonNull
    @Override
    public <IN1, IN2, OUT> Option<OUT> lift(@NonNull final Option<IN1> option1,
                                            @NonNull final Option<IN2> option2,
                                            @NonNull final Func3<T, IN1, IN2, OUT> f) {
        return option1.lift(option2, (o1, o2) -> f.call(mValue, o1, o2));
    }

    @NonNull
    @Override
    public <IN1, IN2, IN3, OUT> Option<OUT> lift(@NonNull final Option<IN1> option1,
                                                 @NonNull final Option<IN2> option2,
                                                 @NonNull final Option<IN3> option3,
                                                 @NonNull final Func4<T, IN1, IN2, IN3, OUT> f) {
        return option1.lift(option2, option3, (o1, o2, o3) -> f.call(mValue, o1, o2, o3));
    }

    @NonNull
    @Override
    public <IN, OUT> Option<OUT> lift(@NonNull final List<? extends Option<? extends IN>> options,
                                      @NonNull final FuncN<? extends OUT> f) {
        return options.size() == 1
                ? lift(first(options), (o1, o2) -> f.call(o1, o2))
                : first(options).lift(tail(options), list -> f.call(combine(mValue, list)));
    }

    @NonNull
    private static <T> T first(@NonNull final List<? extends T> options) {
        return options.get(0);
    }

    @NonNull
    private static <T> List<? extends T> tail(@NonNull final List<? extends T> options) {
        return options.subList(1, options.size());
    }

    @NonNull
    private static Object[] combine(@NonNull final Object[] a, @NonNull final Object[] b) {
        final int length = a.length + b.length;
        Object[] result = new Object[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    @NonNull
    private static Object[] combine(@NonNull final Object a, @NonNull final Object[] b) {
        return combine(new Object[]{a}, b);
    }

    @Override
    public int hashCode() {
        return mValue.hashCode();
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(final Object o) {
        return Option.ofObj(o)
                     .ofType(Some.class)
                     .filter(some -> some.getUnsafe().equals(mValue)) != Option.NONE;
    }

    @Override
    public String toString() {
        return mValue.toString();
    }
}
