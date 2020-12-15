// FILE: Int.java

import java.lang.annotation.*;

<error descr="[WRONG_ANNOTATION_TARGET] This annotation is not applicable to target 'interface'">@Target(<error descr="[TYPE_MISMATCH] Type mismatch: inferred type is ElementType but AnnotationTarget was expected">ElementType.TYPE</error>)</error>
<error descr="[WRONG_ANNOTATION_TARGET] This annotation is not applicable to target 'interface'">@Retention(<error descr="[TYPE_MISMATCH] Type mismatch: inferred type is RetentionPolicy but AnnotationRetention was expected">RetentionPolicy.CLASS</error>)</error>
<error descr="Expected annotation identifier after '@'">@</error>interface Int {
    <error descr="Expecting member declaration">Class</error> value<error descr="Expecting member declaration">(</error><error descr="Expecting member declaration">)</error>;
}

// FILE: test.kt

@Int(String.class<error descr="Name expected">)</error>
public <error descr="[DECLARATION_IN_ILLEGAL_CONTEXT] Declarations are not allowed in this position">class Test {
}</error><EOLError descr="Expecting a top level declaration"></EOLError><EOLError descr="Expecting ')'"></EOLError>