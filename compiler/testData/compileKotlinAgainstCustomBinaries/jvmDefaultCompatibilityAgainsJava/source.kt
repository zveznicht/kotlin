import test.*

interface KotlinInerface<K> : JavaInterface<String> {

}

interface KotlinInerface2 : JavaInterface2<String> {

}

open class KotlinClass : JavaInterface<String> {

}

open class KotlinClass2 : JavaInterface2<String> {

}

open class KotlinClass_2 : KotlinClass() {

}

open class KotlinClass2_2 : KotlinClass2() {

}