fun foo(x: Int) {}

// SYMBOLS:
/*
FirKtFunctionValueParameterSymbol:
  name: x
  origin: SOURCE
  symbolKind: LOCAL
  type: kotlin/Int

FirKtFunctionSymbol:
  fqName: foo
  isExtension: false
  isOperator: false
  isSuspend: false
  name: foo
  origin: SOURCE
  symbolKind: TOP_LEVEL
  type: kotlin/Unit
  typeParameters: []
  valueParameters: [FirKtFunctionValueParameterSymbol(x)]
*/
