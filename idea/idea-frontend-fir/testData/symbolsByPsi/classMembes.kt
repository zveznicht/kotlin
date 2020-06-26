class A {
    val a: Int = 10
    fun x() = 10
}

// SYMBOLS:
/*
FirKtPropertySymbol:
  fqName: A.a
  isExtension: false
  isVal: true
  name: a
  origin: SOURCE
  receiverType: kotlin/Int
  symbolKind: MEMBER
  type: kotlin/Int

FirKtFunctionSymbol:
  fqName: A.x
  isExtension: false
  isOperator: false
  isSuspend: false
  name: x
  origin: SOURCE
  symbolKind: MEMBER
  type: kotlin/Int
  typeParameters: []
  valueParameters: []

FirKtClassOrObjectSymbol:
  classId: A
  classKind: CLASS
  name: A
  origin: SOURCE
  symbolKind: TOP_LEVEL
  typeParameters: []
*/
