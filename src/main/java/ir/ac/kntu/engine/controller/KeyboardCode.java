package ir.ac.kntu.engine.controller;

import javafx.scene.input.KeyCode;

/**
 * All inputs recognized by the engine
 */
public enum KeyboardCode {
    A(KeyCode.A), B(KeyCode.B), C(KeyCode.C), D(KeyCode.D), E(KeyCode.E), F(KeyCode.F), G(KeyCode.G), H(KeyCode.H), I(KeyCode.I), J(KeyCode.J), K(KeyCode.K),
    L(KeyCode.L), M(KeyCode.M), N(KeyCode.N), O(KeyCode.O), P(KeyCode.P), Q(KeyCode.Q), R(KeyCode.R), S(KeyCode.S), T(KeyCode.T), U(KeyCode.U), V(KeyCode.V),
    W(KeyCode.W), X(KeyCode.X), Y(KeyCode.Y), Z(KeyCode.Z),
    UP(KeyCode.UP), DOWN(KeyCode.DOWN), LEFT(KeyCode.LEFT), RIGHT(KeyCode.RIGHT);

    public final KeyCode code;
    private KeyboardCode(KeyCode code){
        this.code = code;
    }
}