fun <X> foo(x: X) {}

// SYMBOLS:
/*
FirKtTypeParameterSymbol:
  name: X
  origin: SOURCE

FirKtFunctionValueParameterSymbol:
  name: x
  origin: SOURCE
  symbolKind: LOCAL
  type: X

FirKtFunctionSymbol:
  fqName: foo
  isExtension: false
  isOperator: false
  isSuspend: false
  name: foo
  origin: SOURCE
  symbolKind: TOP_LEVEL
  type: kotlin/Unit
  typeParameters: [FirKtTypeParameterSymbol(X)]
  valueParameters: [FirKtFunctionValueParameterSymbol(x)]
*/
