package org.w3c.dom.svg

@kotlin.internal.InlineOnly public inline fun SVGBoundingBoxOptions(/*0*/ fill: kotlin.Boolean? = ..., /*1*/ stroke: kotlin.Boolean? = ..., /*2*/ markers: kotlin.Boolean? = ..., /*3*/ clipped: kotlin.Boolean? = ...): org.w3c.dom.svg.SVGBoundingBoxOptions
@kotlin.internal.InlineOnly public inline operator fun org.w3c.dom.svg.SVGLengthList.get(/*0*/ index: kotlin.Int): org.w3c.dom.svg.SVGLength?
@kotlin.internal.InlineOnly public inline operator fun org.w3c.dom.svg.SVGNameList.get(/*0*/ index: kotlin.Int): dynamic
@kotlin.internal.InlineOnly public inline operator fun org.w3c.dom.svg.SVGNumberList.get(/*0*/ index: kotlin.Int): org.w3c.dom.svg.SVGNumber?
@kotlin.internal.InlineOnly public inline operator fun org.w3c.dom.svg.SVGPointList.get(/*0*/ index: kotlin.Int): org.w3c.dom.DOMPoint?
@kotlin.internal.InlineOnly public inline operator fun org.w3c.dom.svg.SVGStringList.get(/*0*/ index: kotlin.Int): kotlin.String?
@kotlin.internal.InlineOnly public inline operator fun org.w3c.dom.svg.SVGTransformList.get(/*0*/ index: kotlin.Int): org.w3c.dom.svg.SVGTransform?
@kotlin.internal.InlineOnly public inline operator fun org.w3c.dom.svg.SVGLengthList.set(/*0*/ index: kotlin.Int, /*1*/ newItem: org.w3c.dom.svg.SVGLength): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun org.w3c.dom.svg.SVGNameList.set(/*0*/ index: kotlin.Int, /*1*/ newItem: dynamic): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun org.w3c.dom.svg.SVGNumberList.set(/*0*/ index: kotlin.Int, /*1*/ newItem: org.w3c.dom.svg.SVGNumber): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun org.w3c.dom.svg.SVGPointList.set(/*0*/ index: kotlin.Int, /*1*/ newItem: org.w3c.dom.DOMPoint): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun org.w3c.dom.svg.SVGStringList.set(/*0*/ index: kotlin.Int, /*1*/ newItem: kotlin.String): kotlin.Unit
@kotlin.internal.InlineOnly public inline operator fun org.w3c.dom.svg.SVGTransformList.set(/*0*/ index: kotlin.Int, /*1*/ newItem: org.w3c.dom.svg.SVGTransform): kotlin.Unit

public external interface GetSVGDocument {
    public abstract fun getSVGDocument(): org.w3c.dom.Document
}

public abstract external class SVGAElement : org.w3c.dom.svg.SVGGraphicsElement, org.w3c.dom.svg.SVGURIReference {
    /*primary*/ public constructor SVGAElement()
    public open val download: org.w3c.dom.svg.SVGAnimatedString
        public open fun <get-download>(): org.w3c.dom.svg.SVGAnimatedString
    public open val hreflang: org.w3c.dom.svg.SVGAnimatedString
        public open fun <get-hreflang>(): org.w3c.dom.svg.SVGAnimatedString
    public open val rel: org.w3c.dom.svg.SVGAnimatedString
        public open fun <get-rel>(): org.w3c.dom.svg.SVGAnimatedString
    public open val relList: org.w3c.dom.svg.SVGAnimatedString
        public open fun <get-relList>(): org.w3c.dom.svg.SVGAnimatedString
    public open val target: org.w3c.dom.svg.SVGAnimatedString
        public open fun <get-target>(): org.w3c.dom.svg.SVGAnimatedString
    public open val type: org.w3c.dom.svg.SVGAnimatedString
        public open fun <get-type>(): org.w3c.dom.svg.SVGAnimatedString

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGAngle {
    /*primary*/ public constructor SVGAngle()
    public open val unitType: kotlin.Short
        public open fun <get-unitType>(): kotlin.Short
    public open var value: kotlin.Float
        public open fun <get-value>(): kotlin.Float
        public open fun <set-value>(/*0*/ <set-?>: kotlin.Float): kotlin.Unit
    public open var valueAsString: kotlin.String
        public open fun <get-valueAsString>(): kotlin.String
        public open fun <set-valueAsString>(/*0*/ <set-?>: kotlin.String): kotlin.Unit
    public open var valueInSpecifiedUnits: kotlin.Float
        public open fun <get-valueInSpecifiedUnits>(): kotlin.Float
        public open fun <set-valueInSpecifiedUnits>(/*0*/ <set-?>: kotlin.Float): kotlin.Unit
    public final fun convertToSpecifiedUnits(/*0*/ unitType: kotlin.Short): kotlin.Unit
    public final fun newValueSpecifiedUnits(/*0*/ unitType: kotlin.Short, /*1*/ valueInSpecifiedUnits: kotlin.Float): kotlin.Unit

    public companion object Companion {
        public final val SVG_ANGLETYPE_DEG: kotlin.Short
            public final fun <get-SVG_ANGLETYPE_DEG>(): kotlin.Short
        public final val SVG_ANGLETYPE_GRAD: kotlin.Short
            public final fun <get-SVG_ANGLETYPE_GRAD>(): kotlin.Short
        public final val SVG_ANGLETYPE_RAD: kotlin.Short
            public final fun <get-SVG_ANGLETYPE_RAD>(): kotlin.Short
        public final val SVG_ANGLETYPE_UNKNOWN: kotlin.Short
            public final fun <get-SVG_ANGLETYPE_UNKNOWN>(): kotlin.Short
        public final val SVG_ANGLETYPE_UNSPECIFIED: kotlin.Short
            public final fun <get-SVG_ANGLETYPE_UNSPECIFIED>(): kotlin.Short
    }
}

public abstract external class SVGAnimatedAngle {
    /*primary*/ public constructor SVGAnimatedAngle()
    public open val animVal: org.w3c.dom.svg.SVGAngle
        public open fun <get-animVal>(): org.w3c.dom.svg.SVGAngle
    public open val baseVal: org.w3c.dom.svg.SVGAngle
        public open fun <get-baseVal>(): org.w3c.dom.svg.SVGAngle
}

public abstract external class SVGAnimatedBoolean {
    /*primary*/ public constructor SVGAnimatedBoolean()
    public open val animVal: kotlin.Boolean
        public open fun <get-animVal>(): kotlin.Boolean
    public open var baseVal: kotlin.Boolean
        public open fun <get-baseVal>(): kotlin.Boolean
        public open fun <set-baseVal>(/*0*/ <set-?>: kotlin.Boolean): kotlin.Unit
}

public abstract external class SVGAnimatedEnumeration {
    /*primary*/ public constructor SVGAnimatedEnumeration()
    public open val animVal: kotlin.Short
        public open fun <get-animVal>(): kotlin.Short
    public open var baseVal: kotlin.Short
        public open fun <get-baseVal>(): kotlin.Short
        public open fun <set-baseVal>(/*0*/ <set-?>: kotlin.Short): kotlin.Unit
}

public abstract external class SVGAnimatedInteger {
    /*primary*/ public constructor SVGAnimatedInteger()
    public open val animVal: kotlin.Int
        public open fun <get-animVal>(): kotlin.Int
    public open var baseVal: kotlin.Int
        public open fun <get-baseVal>(): kotlin.Int
        public open fun <set-baseVal>(/*0*/ <set-?>: kotlin.Int): kotlin.Unit
}

public abstract external class SVGAnimatedLength {
    /*primary*/ public constructor SVGAnimatedLength()
    public open val animVal: org.w3c.dom.svg.SVGLength
        public open fun <get-animVal>(): org.w3c.dom.svg.SVGLength
    public open val baseVal: org.w3c.dom.svg.SVGLength
        public open fun <get-baseVal>(): org.w3c.dom.svg.SVGLength
}

public abstract external class SVGAnimatedLengthList {
    /*primary*/ public constructor SVGAnimatedLengthList()
    public open val animVal: org.w3c.dom.svg.SVGLengthList
        public open fun <get-animVal>(): org.w3c.dom.svg.SVGLengthList
    public open val baseVal: org.w3c.dom.svg.SVGLengthList
        public open fun <get-baseVal>(): org.w3c.dom.svg.SVGLengthList
}

public abstract external class SVGAnimatedNumber {
    /*primary*/ public constructor SVGAnimatedNumber()
    public open val animVal: kotlin.Float
        public open fun <get-animVal>(): kotlin.Float
    public open var baseVal: kotlin.Float
        public open fun <get-baseVal>(): kotlin.Float
        public open fun <set-baseVal>(/*0*/ <set-?>: kotlin.Float): kotlin.Unit
}

public abstract external class SVGAnimatedNumberList {
    /*primary*/ public constructor SVGAnimatedNumberList()
    public open val animVal: org.w3c.dom.svg.SVGNumberList
        public open fun <get-animVal>(): org.w3c.dom.svg.SVGNumberList
    public open val baseVal: org.w3c.dom.svg.SVGNumberList
        public open fun <get-baseVal>(): org.w3c.dom.svg.SVGNumberList
}

public external interface SVGAnimatedPoints {
    public abstract val animatedPoints: org.w3c.dom.svg.SVGPointList
        public abstract fun <get-animatedPoints>(): org.w3c.dom.svg.SVGPointList
    public abstract val points: org.w3c.dom.svg.SVGPointList
        public abstract fun <get-points>(): org.w3c.dom.svg.SVGPointList
}

public abstract external class SVGAnimatedPreserveAspectRatio {
    /*primary*/ public constructor SVGAnimatedPreserveAspectRatio()
    public open val animVal: org.w3c.dom.svg.SVGPreserveAspectRatio
        public open fun <get-animVal>(): org.w3c.dom.svg.SVGPreserveAspectRatio
    public open val baseVal: org.w3c.dom.svg.SVGPreserveAspectRatio
        public open fun <get-baseVal>(): org.w3c.dom.svg.SVGPreserveAspectRatio
}

public abstract external class SVGAnimatedRect {
    /*primary*/ public constructor SVGAnimatedRect()
    public open val animVal: org.w3c.dom.DOMRectReadOnly
        public open fun <get-animVal>(): org.w3c.dom.DOMRectReadOnly
    public open val baseVal: org.w3c.dom.DOMRect
        public open fun <get-baseVal>(): org.w3c.dom.DOMRect
}

public abstract external class SVGAnimatedString {
    /*primary*/ public constructor SVGAnimatedString()
    public open val animVal: kotlin.String
        public open fun <get-animVal>(): kotlin.String
    public open var baseVal: kotlin.String
        public open fun <get-baseVal>(): kotlin.String
        public open fun <set-baseVal>(/*0*/ <set-?>: kotlin.String): kotlin.Unit
}

public abstract external class SVGAnimatedTransformList {
    /*primary*/ public constructor SVGAnimatedTransformList()
    public open val animVal: org.w3c.dom.svg.SVGTransformList
        public open fun <get-animVal>(): org.w3c.dom.svg.SVGTransformList
    public open val baseVal: org.w3c.dom.svg.SVGTransformList
        public open fun <get-baseVal>(): org.w3c.dom.svg.SVGTransformList
}

public external interface SVGBoundingBoxOptions {
    public open var clipped: kotlin.Boolean?
        public open fun <get-clipped>(): kotlin.Boolean?
        public open fun <set-clipped>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open var fill: kotlin.Boolean?
        public open fun <get-fill>(): kotlin.Boolean?
        public open fun <set-fill>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open var markers: kotlin.Boolean?
        public open fun <get-markers>(): kotlin.Boolean?
        public open fun <set-markers>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
    public open var stroke: kotlin.Boolean?
        public open fun <get-stroke>(): kotlin.Boolean?
        public open fun <set-stroke>(/*0*/ value: kotlin.Boolean?): kotlin.Unit
}

public abstract external class SVGCircleElement : org.w3c.dom.svg.SVGGeometryElement {
    /*primary*/ public constructor SVGCircleElement()
    public open val cx: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-cx>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val cy: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-cy>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val r: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-r>(): org.w3c.dom.svg.SVGAnimatedLength

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGCursorElement : org.w3c.dom.svg.SVGElement, org.w3c.dom.svg.SVGURIReference {
    /*primary*/ public constructor SVGCursorElement()
    public open val x: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-x>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val y: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-y>(): org.w3c.dom.svg.SVGAnimatedLength

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGDefsElement : org.w3c.dom.svg.SVGGraphicsElement {
    /*primary*/ public constructor SVGDefsElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGDescElement : org.w3c.dom.svg.SVGElement {
    /*primary*/ public constructor SVGDescElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGElement : org.w3c.dom.Element, org.w3c.dom.css.ElementCSSInlineStyle, org.w3c.dom.GlobalEventHandlers, org.w3c.dom.svg.SVGElementInstance {
    /*primary*/ public constructor SVGElement()
    public open val dataset: org.w3c.dom.DOMStringMap
        public open fun <get-dataset>(): org.w3c.dom.DOMStringMap
    public open val ownerSVGElement: org.w3c.dom.svg.SVGSVGElement?
        public open fun <get-ownerSVGElement>(): org.w3c.dom.svg.SVGSVGElement?
    public open var tabIndex: kotlin.Int
        public open fun <get-tabIndex>(): kotlin.Int
        public open fun <set-tabIndex>(/*0*/ <set-?>: kotlin.Int): kotlin.Unit
    public open val viewportElement: org.w3c.dom.svg.SVGElement?
        public open fun <get-viewportElement>(): org.w3c.dom.svg.SVGElement?
    public final fun blur(): kotlin.Unit
    public final fun focus(): kotlin.Unit

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public external interface SVGElementInstance {
    public open val correspondingElement: org.w3c.dom.svg.SVGElement?
        public open fun <get-correspondingElement>(): org.w3c.dom.svg.SVGElement?
    public open val correspondingUseElement: org.w3c.dom.svg.SVGUseElement?
        public open fun <get-correspondingUseElement>(): org.w3c.dom.svg.SVGUseElement?
}

public abstract external class SVGEllipseElement : org.w3c.dom.svg.SVGGeometryElement {
    /*primary*/ public constructor SVGEllipseElement()
    public open val cx: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-cx>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val cy: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-cy>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val rx: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-rx>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val ry: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-ry>(): org.w3c.dom.svg.SVGAnimatedLength

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public external interface SVGFitToViewBox {
    public abstract val preserveAspectRatio: org.w3c.dom.svg.SVGAnimatedPreserveAspectRatio
        public abstract fun <get-preserveAspectRatio>(): org.w3c.dom.svg.SVGAnimatedPreserveAspectRatio
    public abstract val viewBox: org.w3c.dom.svg.SVGAnimatedRect
        public abstract fun <get-viewBox>(): org.w3c.dom.svg.SVGAnimatedRect
}

public abstract external class SVGForeignObjectElement : org.w3c.dom.svg.SVGGraphicsElement {
    /*primary*/ public constructor SVGForeignObjectElement()
    public open val height: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-height>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val width: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-width>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val x: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-x>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val y: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-y>(): org.w3c.dom.svg.SVGAnimatedLength

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGGElement : org.w3c.dom.svg.SVGGraphicsElement {
    /*primary*/ public constructor SVGGElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGGeometryElement : org.w3c.dom.svg.SVGGraphicsElement {
    /*primary*/ public constructor SVGGeometryElement()
    public open val pathLength: org.w3c.dom.svg.SVGAnimatedNumber
        public open fun <get-pathLength>(): org.w3c.dom.svg.SVGAnimatedNumber
    public final fun getPointAtLength(/*0*/ distance: kotlin.Float): org.w3c.dom.DOMPoint
    public final fun getTotalLength(): kotlin.Float
    public final fun isPointInFill(/*0*/ point: org.w3c.dom.DOMPoint): kotlin.Boolean
    public final fun isPointInStroke(/*0*/ point: org.w3c.dom.DOMPoint): kotlin.Boolean

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGGradientElement : org.w3c.dom.svg.SVGElement, org.w3c.dom.svg.SVGURIReference, org.w3c.dom.svg.SVGUnitTypes {
    /*primary*/ public constructor SVGGradientElement()
    public open val gradientTransform: org.w3c.dom.svg.SVGAnimatedTransformList
        public open fun <get-gradientTransform>(): org.w3c.dom.svg.SVGAnimatedTransformList
    public open val gradientUnits: org.w3c.dom.svg.SVGAnimatedEnumeration
        public open fun <get-gradientUnits>(): org.w3c.dom.svg.SVGAnimatedEnumeration
    public open val spreadMethod: org.w3c.dom.svg.SVGAnimatedEnumeration
        public open fun <get-spreadMethod>(): org.w3c.dom.svg.SVGAnimatedEnumeration

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val SVG_SPREADMETHOD_PAD: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_PAD>(): kotlin.Short
        public final val SVG_SPREADMETHOD_REFLECT: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_REFLECT>(): kotlin.Short
        public final val SVG_SPREADMETHOD_REPEAT: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_REPEAT>(): kotlin.Short
        public final val SVG_SPREADMETHOD_UNKNOWN: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_UNKNOWN>(): kotlin.Short
        public final val SVG_UNIT_TYPE_OBJECTBOUNDINGBOX: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_OBJECTBOUNDINGBOX>(): kotlin.Short
        public final val SVG_UNIT_TYPE_UNKNOWN: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_UNKNOWN>(): kotlin.Short
        public final val SVG_UNIT_TYPE_USERSPACEONUSE: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_USERSPACEONUSE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGGraphicsElement : org.w3c.dom.svg.SVGElement, org.w3c.dom.svg.SVGTests {
    /*primary*/ public constructor SVGGraphicsElement()
    public open val transform: org.w3c.dom.svg.SVGAnimatedTransformList
        public open fun <get-transform>(): org.w3c.dom.svg.SVGAnimatedTransformList
    public final fun getBBox(/*0*/ options: org.w3c.dom.svg.SVGBoundingBoxOptions = ...): org.w3c.dom.DOMRect
    public final fun getCTM(): org.w3c.dom.DOMMatrix?
    public final fun getScreenCTM(): org.w3c.dom.DOMMatrix?

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGHatchElement : org.w3c.dom.svg.SVGElement {
    /*primary*/ public constructor SVGHatchElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGHatchpathElement : org.w3c.dom.svg.SVGElement {
    /*primary*/ public constructor SVGHatchpathElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGImageElement : org.w3c.dom.svg.SVGGraphicsElement, org.w3c.dom.svg.SVGURIReference, org.w3c.dom.HTMLOrSVGImageElement {
    /*primary*/ public constructor SVGImageElement()
    public open var crossOrigin: kotlin.String?
        public open fun <get-crossOrigin>(): kotlin.String?
        public open fun <set-crossOrigin>(/*0*/ <set-?>: kotlin.String?): kotlin.Unit
    public open val height: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-height>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val preserveAspectRatio: org.w3c.dom.svg.SVGAnimatedPreserveAspectRatio
        public open fun <get-preserveAspectRatio>(): org.w3c.dom.svg.SVGAnimatedPreserveAspectRatio
    public open val width: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-width>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val x: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-x>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val y: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-y>(): org.w3c.dom.svg.SVGAnimatedLength

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGLength {
    /*primary*/ public constructor SVGLength()
    public open val unitType: kotlin.Short
        public open fun <get-unitType>(): kotlin.Short
    public open var value: kotlin.Float
        public open fun <get-value>(): kotlin.Float
        public open fun <set-value>(/*0*/ <set-?>: kotlin.Float): kotlin.Unit
    public open var valueAsString: kotlin.String
        public open fun <get-valueAsString>(): kotlin.String
        public open fun <set-valueAsString>(/*0*/ <set-?>: kotlin.String): kotlin.Unit
    public open var valueInSpecifiedUnits: kotlin.Float
        public open fun <get-valueInSpecifiedUnits>(): kotlin.Float
        public open fun <set-valueInSpecifiedUnits>(/*0*/ <set-?>: kotlin.Float): kotlin.Unit
    public final fun convertToSpecifiedUnits(/*0*/ unitType: kotlin.Short): kotlin.Unit
    public final fun newValueSpecifiedUnits(/*0*/ unitType: kotlin.Short, /*1*/ valueInSpecifiedUnits: kotlin.Float): kotlin.Unit

    public companion object Companion {
        public final val SVG_LENGTHTYPE_CM: kotlin.Short
            public final fun <get-SVG_LENGTHTYPE_CM>(): kotlin.Short
        public final val SVG_LENGTHTYPE_EMS: kotlin.Short
            public final fun <get-SVG_LENGTHTYPE_EMS>(): kotlin.Short
        public final val SVG_LENGTHTYPE_EXS: kotlin.Short
            public final fun <get-SVG_LENGTHTYPE_EXS>(): kotlin.Short
        public final val SVG_LENGTHTYPE_IN: kotlin.Short
            public final fun <get-SVG_LENGTHTYPE_IN>(): kotlin.Short
        public final val SVG_LENGTHTYPE_MM: kotlin.Short
            public final fun <get-SVG_LENGTHTYPE_MM>(): kotlin.Short
        public final val SVG_LENGTHTYPE_NUMBER: kotlin.Short
            public final fun <get-SVG_LENGTHTYPE_NUMBER>(): kotlin.Short
        public final val SVG_LENGTHTYPE_PC: kotlin.Short
            public final fun <get-SVG_LENGTHTYPE_PC>(): kotlin.Short
        public final val SVG_LENGTHTYPE_PERCENTAGE: kotlin.Short
            public final fun <get-SVG_LENGTHTYPE_PERCENTAGE>(): kotlin.Short
        public final val SVG_LENGTHTYPE_PT: kotlin.Short
            public final fun <get-SVG_LENGTHTYPE_PT>(): kotlin.Short
        public final val SVG_LENGTHTYPE_PX: kotlin.Short
            public final fun <get-SVG_LENGTHTYPE_PX>(): kotlin.Short
        public final val SVG_LENGTHTYPE_UNKNOWN: kotlin.Short
            public final fun <get-SVG_LENGTHTYPE_UNKNOWN>(): kotlin.Short
    }
}

public abstract external class SVGLengthList {
    /*primary*/ public constructor SVGLengthList()
    public open val length: kotlin.Int
        public open fun <get-length>(): kotlin.Int
    public open val numberOfItems: kotlin.Int
        public open fun <get-numberOfItems>(): kotlin.Int
    public final fun appendItem(/*0*/ newItem: org.w3c.dom.svg.SVGLength): org.w3c.dom.svg.SVGLength
    public final fun clear(): kotlin.Unit
    public final fun getItem(/*0*/ index: kotlin.Int): org.w3c.dom.svg.SVGLength
    public final fun initialize(/*0*/ newItem: org.w3c.dom.svg.SVGLength): org.w3c.dom.svg.SVGLength
    public final fun insertItemBefore(/*0*/ newItem: org.w3c.dom.svg.SVGLength, /*1*/ index: kotlin.Int): org.w3c.dom.svg.SVGLength
    public final fun removeItem(/*0*/ index: kotlin.Int): org.w3c.dom.svg.SVGLength
    public final fun replaceItem(/*0*/ newItem: org.w3c.dom.svg.SVGLength, /*1*/ index: kotlin.Int): org.w3c.dom.svg.SVGLength
}

public abstract external class SVGLineElement : org.w3c.dom.svg.SVGGeometryElement {
    /*primary*/ public constructor SVGLineElement()
    public open val x1: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-x1>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val x2: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-x2>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val y1: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-y1>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val y2: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-y2>(): org.w3c.dom.svg.SVGAnimatedLength

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGLinearGradientElement : org.w3c.dom.svg.SVGGradientElement {
    /*primary*/ public constructor SVGLinearGradientElement()
    public open val x1: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-x1>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val x2: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-x2>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val y1: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-y1>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val y2: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-y2>(): org.w3c.dom.svg.SVGAnimatedLength

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val SVG_SPREADMETHOD_PAD: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_PAD>(): kotlin.Short
        public final val SVG_SPREADMETHOD_REFLECT: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_REFLECT>(): kotlin.Short
        public final val SVG_SPREADMETHOD_REPEAT: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_REPEAT>(): kotlin.Short
        public final val SVG_SPREADMETHOD_UNKNOWN: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_UNKNOWN>(): kotlin.Short
        public final val SVG_UNIT_TYPE_OBJECTBOUNDINGBOX: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_OBJECTBOUNDINGBOX>(): kotlin.Short
        public final val SVG_UNIT_TYPE_UNKNOWN: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_UNKNOWN>(): kotlin.Short
        public final val SVG_UNIT_TYPE_USERSPACEONUSE: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_USERSPACEONUSE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGMarkerElement : org.w3c.dom.svg.SVGElement, org.w3c.dom.svg.SVGFitToViewBox {
    /*primary*/ public constructor SVGMarkerElement()
    public open val markerHeight: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-markerHeight>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val markerUnits: org.w3c.dom.svg.SVGAnimatedEnumeration
        public open fun <get-markerUnits>(): org.w3c.dom.svg.SVGAnimatedEnumeration
    public open val markerWidth: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-markerWidth>(): org.w3c.dom.svg.SVGAnimatedLength
    public open var orient: kotlin.String
        public open fun <get-orient>(): kotlin.String
        public open fun <set-orient>(/*0*/ <set-?>: kotlin.String): kotlin.Unit
    public open val orientAngle: org.w3c.dom.svg.SVGAnimatedAngle
        public open fun <get-orientAngle>(): org.w3c.dom.svg.SVGAnimatedAngle
    public open val orientType: org.w3c.dom.svg.SVGAnimatedEnumeration
        public open fun <get-orientType>(): org.w3c.dom.svg.SVGAnimatedEnumeration
    public open val refX: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-refX>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val refY: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-refY>(): org.w3c.dom.svg.SVGAnimatedLength
    public final fun setOrientToAngle(/*0*/ angle: org.w3c.dom.svg.SVGAngle): kotlin.Unit
    public final fun setOrientToAuto(): kotlin.Unit

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val SVG_MARKERUNITS_STROKEWIDTH: kotlin.Short
            public final fun <get-SVG_MARKERUNITS_STROKEWIDTH>(): kotlin.Short
        public final val SVG_MARKERUNITS_UNKNOWN: kotlin.Short
            public final fun <get-SVG_MARKERUNITS_UNKNOWN>(): kotlin.Short
        public final val SVG_MARKERUNITS_USERSPACEONUSE: kotlin.Short
            public final fun <get-SVG_MARKERUNITS_USERSPACEONUSE>(): kotlin.Short
        public final val SVG_MARKER_ORIENT_ANGLE: kotlin.Short
            public final fun <get-SVG_MARKER_ORIENT_ANGLE>(): kotlin.Short
        public final val SVG_MARKER_ORIENT_AUTO: kotlin.Short
            public final fun <get-SVG_MARKER_ORIENT_AUTO>(): kotlin.Short
        public final val SVG_MARKER_ORIENT_UNKNOWN: kotlin.Short
            public final fun <get-SVG_MARKER_ORIENT_UNKNOWN>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGMeshElement : org.w3c.dom.svg.SVGGeometryElement, org.w3c.dom.svg.SVGURIReference {
    /*primary*/ public constructor SVGMeshElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGMeshGradientElement : org.w3c.dom.svg.SVGGradientElement {
    /*primary*/ public constructor SVGMeshGradientElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val SVG_SPREADMETHOD_PAD: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_PAD>(): kotlin.Short
        public final val SVG_SPREADMETHOD_REFLECT: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_REFLECT>(): kotlin.Short
        public final val SVG_SPREADMETHOD_REPEAT: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_REPEAT>(): kotlin.Short
        public final val SVG_SPREADMETHOD_UNKNOWN: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_UNKNOWN>(): kotlin.Short
        public final val SVG_UNIT_TYPE_OBJECTBOUNDINGBOX: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_OBJECTBOUNDINGBOX>(): kotlin.Short
        public final val SVG_UNIT_TYPE_UNKNOWN: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_UNKNOWN>(): kotlin.Short
        public final val SVG_UNIT_TYPE_USERSPACEONUSE: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_USERSPACEONUSE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGMeshpatchElement : org.w3c.dom.svg.SVGElement {
    /*primary*/ public constructor SVGMeshpatchElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGMeshrowElement : org.w3c.dom.svg.SVGElement {
    /*primary*/ public constructor SVGMeshrowElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGMetadataElement : org.w3c.dom.svg.SVGElement {
    /*primary*/ public constructor SVGMetadataElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGNameList {
    /*primary*/ public constructor SVGNameList()
    public open val length: kotlin.Int
        public open fun <get-length>(): kotlin.Int
    public open val numberOfItems: kotlin.Int
        public open fun <get-numberOfItems>(): kotlin.Int
    public final fun appendItem(/*0*/ newItem: dynamic): dynamic
    public final fun clear(): kotlin.Unit
    public final fun getItem(/*0*/ index: kotlin.Int): dynamic
    public final fun initialize(/*0*/ newItem: dynamic): dynamic
    public final fun insertItemBefore(/*0*/ newItem: dynamic, /*1*/ index: kotlin.Int): dynamic
    public final fun removeItem(/*0*/ index: kotlin.Int): dynamic
    public final fun replaceItem(/*0*/ newItem: dynamic, /*1*/ index: kotlin.Int): dynamic
}

public abstract external class SVGNumber {
    /*primary*/ public constructor SVGNumber()
    public open var value: kotlin.Float
        public open fun <get-value>(): kotlin.Float
        public open fun <set-value>(/*0*/ <set-?>: kotlin.Float): kotlin.Unit
}

public abstract external class SVGNumberList {
    /*primary*/ public constructor SVGNumberList()
    public open val length: kotlin.Int
        public open fun <get-length>(): kotlin.Int
    public open val numberOfItems: kotlin.Int
        public open fun <get-numberOfItems>(): kotlin.Int
    public final fun appendItem(/*0*/ newItem: org.w3c.dom.svg.SVGNumber): org.w3c.dom.svg.SVGNumber
    public final fun clear(): kotlin.Unit
    public final fun getItem(/*0*/ index: kotlin.Int): org.w3c.dom.svg.SVGNumber
    public final fun initialize(/*0*/ newItem: org.w3c.dom.svg.SVGNumber): org.w3c.dom.svg.SVGNumber
    public final fun insertItemBefore(/*0*/ newItem: org.w3c.dom.svg.SVGNumber, /*1*/ index: kotlin.Int): org.w3c.dom.svg.SVGNumber
    public final fun removeItem(/*0*/ index: kotlin.Int): org.w3c.dom.svg.SVGNumber
    public final fun replaceItem(/*0*/ newItem: org.w3c.dom.svg.SVGNumber, /*1*/ index: kotlin.Int): org.w3c.dom.svg.SVGNumber
}

public abstract external class SVGPathElement : org.w3c.dom.svg.SVGGeometryElement {
    /*primary*/ public constructor SVGPathElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGPatternElement : org.w3c.dom.svg.SVGElement, org.w3c.dom.svg.SVGFitToViewBox, org.w3c.dom.svg.SVGURIReference, org.w3c.dom.svg.SVGUnitTypes {
    /*primary*/ public constructor SVGPatternElement()
    public open val height: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-height>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val patternContentUnits: org.w3c.dom.svg.SVGAnimatedEnumeration
        public open fun <get-patternContentUnits>(): org.w3c.dom.svg.SVGAnimatedEnumeration
    public open val patternTransform: org.w3c.dom.svg.SVGAnimatedTransformList
        public open fun <get-patternTransform>(): org.w3c.dom.svg.SVGAnimatedTransformList
    public open val patternUnits: org.w3c.dom.svg.SVGAnimatedEnumeration
        public open fun <get-patternUnits>(): org.w3c.dom.svg.SVGAnimatedEnumeration
    public open val width: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-width>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val x: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-x>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val y: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-y>(): org.w3c.dom.svg.SVGAnimatedLength

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val SVG_UNIT_TYPE_OBJECTBOUNDINGBOX: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_OBJECTBOUNDINGBOX>(): kotlin.Short
        public final val SVG_UNIT_TYPE_UNKNOWN: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_UNKNOWN>(): kotlin.Short
        public final val SVG_UNIT_TYPE_USERSPACEONUSE: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_USERSPACEONUSE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGPointList {
    /*primary*/ public constructor SVGPointList()
    public open val length: kotlin.Int
        public open fun <get-length>(): kotlin.Int
    public open val numberOfItems: kotlin.Int
        public open fun <get-numberOfItems>(): kotlin.Int
    public final fun appendItem(/*0*/ newItem: org.w3c.dom.DOMPoint): org.w3c.dom.DOMPoint
    public final fun clear(): kotlin.Unit
    public final fun getItem(/*0*/ index: kotlin.Int): org.w3c.dom.DOMPoint
    public final fun initialize(/*0*/ newItem: org.w3c.dom.DOMPoint): org.w3c.dom.DOMPoint
    public final fun insertItemBefore(/*0*/ newItem: org.w3c.dom.DOMPoint, /*1*/ index: kotlin.Int): org.w3c.dom.DOMPoint
    public final fun removeItem(/*0*/ index: kotlin.Int): org.w3c.dom.DOMPoint
    public final fun replaceItem(/*0*/ newItem: org.w3c.dom.DOMPoint, /*1*/ index: kotlin.Int): org.w3c.dom.DOMPoint
}

public abstract external class SVGPolygonElement : org.w3c.dom.svg.SVGGeometryElement, org.w3c.dom.svg.SVGAnimatedPoints {
    /*primary*/ public constructor SVGPolygonElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGPolylineElement : org.w3c.dom.svg.SVGGeometryElement, org.w3c.dom.svg.SVGAnimatedPoints {
    /*primary*/ public constructor SVGPolylineElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGPreserveAspectRatio {
    /*primary*/ public constructor SVGPreserveAspectRatio()
    public open var align: kotlin.Short
        public open fun <get-align>(): kotlin.Short
        public open fun <set-align>(/*0*/ <set-?>: kotlin.Short): kotlin.Unit
    public open var meetOrSlice: kotlin.Short
        public open fun <get-meetOrSlice>(): kotlin.Short
        public open fun <set-meetOrSlice>(/*0*/ <set-?>: kotlin.Short): kotlin.Unit

    public companion object Companion {
        public final val SVG_MEETORSLICE_MEET: kotlin.Short
            public final fun <get-SVG_MEETORSLICE_MEET>(): kotlin.Short
        public final val SVG_MEETORSLICE_SLICE: kotlin.Short
            public final fun <get-SVG_MEETORSLICE_SLICE>(): kotlin.Short
        public final val SVG_MEETORSLICE_UNKNOWN: kotlin.Short
            public final fun <get-SVG_MEETORSLICE_UNKNOWN>(): kotlin.Short
        public final val SVG_PRESERVEASPECTRATIO_NONE: kotlin.Short
            public final fun <get-SVG_PRESERVEASPECTRATIO_NONE>(): kotlin.Short
        public final val SVG_PRESERVEASPECTRATIO_UNKNOWN: kotlin.Short
            public final fun <get-SVG_PRESERVEASPECTRATIO_UNKNOWN>(): kotlin.Short
        public final val SVG_PRESERVEASPECTRATIO_XMAXYMAX: kotlin.Short
            public final fun <get-SVG_PRESERVEASPECTRATIO_XMAXYMAX>(): kotlin.Short
        public final val SVG_PRESERVEASPECTRATIO_XMAXYMID: kotlin.Short
            public final fun <get-SVG_PRESERVEASPECTRATIO_XMAXYMID>(): kotlin.Short
        public final val SVG_PRESERVEASPECTRATIO_XMAXYMIN: kotlin.Short
            public final fun <get-SVG_PRESERVEASPECTRATIO_XMAXYMIN>(): kotlin.Short
        public final val SVG_PRESERVEASPECTRATIO_XMIDYMAX: kotlin.Short
            public final fun <get-SVG_PRESERVEASPECTRATIO_XMIDYMAX>(): kotlin.Short
        public final val SVG_PRESERVEASPECTRATIO_XMIDYMID: kotlin.Short
            public final fun <get-SVG_PRESERVEASPECTRATIO_XMIDYMID>(): kotlin.Short
        public final val SVG_PRESERVEASPECTRATIO_XMIDYMIN: kotlin.Short
            public final fun <get-SVG_PRESERVEASPECTRATIO_XMIDYMIN>(): kotlin.Short
        public final val SVG_PRESERVEASPECTRATIO_XMINYMAX: kotlin.Short
            public final fun <get-SVG_PRESERVEASPECTRATIO_XMINYMAX>(): kotlin.Short
        public final val SVG_PRESERVEASPECTRATIO_XMINYMID: kotlin.Short
            public final fun <get-SVG_PRESERVEASPECTRATIO_XMINYMID>(): kotlin.Short
        public final val SVG_PRESERVEASPECTRATIO_XMINYMIN: kotlin.Short
            public final fun <get-SVG_PRESERVEASPECTRATIO_XMINYMIN>(): kotlin.Short
    }
}

public abstract external class SVGRadialGradientElement : org.w3c.dom.svg.SVGGradientElement {
    /*primary*/ public constructor SVGRadialGradientElement()
    public open val cx: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-cx>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val cy: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-cy>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val fr: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-fr>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val fx: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-fx>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val fy: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-fy>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val r: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-r>(): org.w3c.dom.svg.SVGAnimatedLength

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val SVG_SPREADMETHOD_PAD: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_PAD>(): kotlin.Short
        public final val SVG_SPREADMETHOD_REFLECT: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_REFLECT>(): kotlin.Short
        public final val SVG_SPREADMETHOD_REPEAT: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_REPEAT>(): kotlin.Short
        public final val SVG_SPREADMETHOD_UNKNOWN: kotlin.Short
            public final fun <get-SVG_SPREADMETHOD_UNKNOWN>(): kotlin.Short
        public final val SVG_UNIT_TYPE_OBJECTBOUNDINGBOX: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_OBJECTBOUNDINGBOX>(): kotlin.Short
        public final val SVG_UNIT_TYPE_UNKNOWN: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_UNKNOWN>(): kotlin.Short
        public final val SVG_UNIT_TYPE_USERSPACEONUSE: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_USERSPACEONUSE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGRectElement : org.w3c.dom.svg.SVGGeometryElement {
    /*primary*/ public constructor SVGRectElement()
    public open val height: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-height>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val rx: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-rx>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val ry: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-ry>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val width: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-width>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val x: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-x>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val y: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-y>(): org.w3c.dom.svg.SVGAnimatedLength

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGSVGElement : org.w3c.dom.svg.SVGGraphicsElement, org.w3c.dom.svg.SVGFitToViewBox, org.w3c.dom.svg.SVGZoomAndPan, org.w3c.dom.WindowEventHandlers {
    /*primary*/ public constructor SVGSVGElement()
    public open var currentScale: kotlin.Float
        public open fun <get-currentScale>(): kotlin.Float
        public open fun <set-currentScale>(/*0*/ <set-?>: kotlin.Float): kotlin.Unit
    public open val currentTranslate: org.w3c.dom.DOMPointReadOnly
        public open fun <get-currentTranslate>(): org.w3c.dom.DOMPointReadOnly
    public open val height: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-height>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val width: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-width>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val x: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-x>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val y: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-y>(): org.w3c.dom.svg.SVGAnimatedLength
    public final fun checkEnclosure(/*0*/ element: org.w3c.dom.svg.SVGElement, /*1*/ rect: org.w3c.dom.DOMRectReadOnly): kotlin.Boolean
    public final fun checkIntersection(/*0*/ element: org.w3c.dom.svg.SVGElement, /*1*/ rect: org.w3c.dom.DOMRectReadOnly): kotlin.Boolean
    public final fun createSVGAngle(): org.w3c.dom.svg.SVGAngle
    public final fun createSVGLength(): org.w3c.dom.svg.SVGLength
    public final fun createSVGMatrix(): org.w3c.dom.DOMMatrix
    public final fun createSVGNumber(): org.w3c.dom.svg.SVGNumber
    public final fun createSVGPoint(): org.w3c.dom.DOMPoint
    public final fun createSVGRect(): org.w3c.dom.DOMRect
    public final fun createSVGTransform(): org.w3c.dom.svg.SVGTransform
    public final fun createSVGTransformFromMatrix(/*0*/ matrix: org.w3c.dom.DOMMatrixReadOnly): org.w3c.dom.svg.SVGTransform
    public final fun deselectAll(): kotlin.Unit
    public final fun forceRedraw(): kotlin.Unit
    public final fun getElementById(/*0*/ elementId: kotlin.String): org.w3c.dom.Element
    public final fun getEnclosureList(/*0*/ rect: org.w3c.dom.DOMRectReadOnly, /*1*/ referenceElement: org.w3c.dom.svg.SVGElement?): org.w3c.dom.NodeList
    public final fun getIntersectionList(/*0*/ rect: org.w3c.dom.DOMRectReadOnly, /*1*/ referenceElement: org.w3c.dom.svg.SVGElement?): org.w3c.dom.NodeList
    public final fun suspendRedraw(/*0*/ maxWaitMilliseconds: kotlin.Int): kotlin.Int
    public final fun unsuspendRedraw(/*0*/ suspendHandleID: kotlin.Int): kotlin.Unit
    public final fun unsuspendRedrawAll(): kotlin.Unit

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val SVG_ZOOMANDPAN_DISABLE: kotlin.Short
            public final fun <get-SVG_ZOOMANDPAN_DISABLE>(): kotlin.Short
        public final val SVG_ZOOMANDPAN_MAGNIFY: kotlin.Short
            public final fun <get-SVG_ZOOMANDPAN_MAGNIFY>(): kotlin.Short
        public final val SVG_ZOOMANDPAN_UNKNOWN: kotlin.Short
            public final fun <get-SVG_ZOOMANDPAN_UNKNOWN>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGScriptElement : org.w3c.dom.svg.SVGElement, org.w3c.dom.svg.SVGURIReference, org.w3c.dom.HTMLOrSVGScriptElement {
    /*primary*/ public constructor SVGScriptElement()
    public open var crossOrigin: kotlin.String?
        public open fun <get-crossOrigin>(): kotlin.String?
        public open fun <set-crossOrigin>(/*0*/ <set-?>: kotlin.String?): kotlin.Unit
    public open var type: kotlin.String
        public open fun <get-type>(): kotlin.String
        public open fun <set-type>(/*0*/ <set-?>: kotlin.String): kotlin.Unit

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGSolidcolorElement : org.w3c.dom.svg.SVGElement {
    /*primary*/ public constructor SVGSolidcolorElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGStopElement : org.w3c.dom.svg.SVGElement {
    /*primary*/ public constructor SVGStopElement()
    public open val offset: org.w3c.dom.svg.SVGAnimatedNumber
        public open fun <get-offset>(): org.w3c.dom.svg.SVGAnimatedNumber

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGStringList {
    /*primary*/ public constructor SVGStringList()
    public open val length: kotlin.Int
        public open fun <get-length>(): kotlin.Int
    public open val numberOfItems: kotlin.Int
        public open fun <get-numberOfItems>(): kotlin.Int
    public final fun appendItem(/*0*/ newItem: kotlin.String): kotlin.String
    public final fun clear(): kotlin.Unit
    public final fun getItem(/*0*/ index: kotlin.Int): kotlin.String
    public final fun initialize(/*0*/ newItem: kotlin.String): kotlin.String
    public final fun insertItemBefore(/*0*/ newItem: kotlin.String, /*1*/ index: kotlin.Int): kotlin.String
    public final fun removeItem(/*0*/ index: kotlin.Int): kotlin.String
    public final fun replaceItem(/*0*/ newItem: kotlin.String, /*1*/ index: kotlin.Int): kotlin.String
}

public abstract external class SVGStyleElement : org.w3c.dom.svg.SVGElement, org.w3c.dom.css.LinkStyle {
    /*primary*/ public constructor SVGStyleElement()
    public open var media: kotlin.String
        public open fun <get-media>(): kotlin.String
        public open fun <set-media>(/*0*/ <set-?>: kotlin.String): kotlin.Unit
    public open var title: kotlin.String
        public open fun <get-title>(): kotlin.String
        public open fun <set-title>(/*0*/ <set-?>: kotlin.String): kotlin.Unit
    public open var type: kotlin.String
        public open fun <get-type>(): kotlin.String
        public open fun <set-type>(/*0*/ <set-?>: kotlin.String): kotlin.Unit

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGSwitchElement : org.w3c.dom.svg.SVGGraphicsElement {
    /*primary*/ public constructor SVGSwitchElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGSymbolElement : org.w3c.dom.svg.SVGGraphicsElement, org.w3c.dom.svg.SVGFitToViewBox {
    /*primary*/ public constructor SVGSymbolElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGTSpanElement : org.w3c.dom.svg.SVGTextPositioningElement {
    /*primary*/ public constructor SVGTSpanElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val LENGTHADJUST_SPACING: kotlin.Short
            public final fun <get-LENGTHADJUST_SPACING>(): kotlin.Short
        public final val LENGTHADJUST_SPACINGANDGLYPHS: kotlin.Short
            public final fun <get-LENGTHADJUST_SPACINGANDGLYPHS>(): kotlin.Short
        public final val LENGTHADJUST_UNKNOWN: kotlin.Short
            public final fun <get-LENGTHADJUST_UNKNOWN>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public external interface SVGTests {
    public abstract val requiredExtensions: org.w3c.dom.svg.SVGStringList
        public abstract fun <get-requiredExtensions>(): org.w3c.dom.svg.SVGStringList
    public abstract val systemLanguage: org.w3c.dom.svg.SVGStringList
        public abstract fun <get-systemLanguage>(): org.w3c.dom.svg.SVGStringList
}

public abstract external class SVGTextContentElement : org.w3c.dom.svg.SVGGraphicsElement {
    /*primary*/ public constructor SVGTextContentElement()
    public open val lengthAdjust: org.w3c.dom.svg.SVGAnimatedEnumeration
        public open fun <get-lengthAdjust>(): org.w3c.dom.svg.SVGAnimatedEnumeration
    public open val textLength: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-textLength>(): org.w3c.dom.svg.SVGAnimatedLength
    public final fun getCharNumAtPosition(/*0*/ point: org.w3c.dom.DOMPoint): kotlin.Int
    public final fun getComputedTextLength(): kotlin.Float
    public final fun getEndPositionOfChar(/*0*/ charnum: kotlin.Int): org.w3c.dom.DOMPoint
    public final fun getExtentOfChar(/*0*/ charnum: kotlin.Int): org.w3c.dom.DOMRect
    public final fun getNumberOfChars(): kotlin.Int
    public final fun getRotationOfChar(/*0*/ charnum: kotlin.Int): kotlin.Float
    public final fun getStartPositionOfChar(/*0*/ charnum: kotlin.Int): org.w3c.dom.DOMPoint
    public final fun getSubStringLength(/*0*/ charnum: kotlin.Int, /*1*/ nchars: kotlin.Int): kotlin.Float
    public final fun selectSubString(/*0*/ charnum: kotlin.Int, /*1*/ nchars: kotlin.Int): kotlin.Unit

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val LENGTHADJUST_SPACING: kotlin.Short
            public final fun <get-LENGTHADJUST_SPACING>(): kotlin.Short
        public final val LENGTHADJUST_SPACINGANDGLYPHS: kotlin.Short
            public final fun <get-LENGTHADJUST_SPACINGANDGLYPHS>(): kotlin.Short
        public final val LENGTHADJUST_UNKNOWN: kotlin.Short
            public final fun <get-LENGTHADJUST_UNKNOWN>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGTextElement : org.w3c.dom.svg.SVGTextPositioningElement {
    /*primary*/ public constructor SVGTextElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val LENGTHADJUST_SPACING: kotlin.Short
            public final fun <get-LENGTHADJUST_SPACING>(): kotlin.Short
        public final val LENGTHADJUST_SPACINGANDGLYPHS: kotlin.Short
            public final fun <get-LENGTHADJUST_SPACINGANDGLYPHS>(): kotlin.Short
        public final val LENGTHADJUST_UNKNOWN: kotlin.Short
            public final fun <get-LENGTHADJUST_UNKNOWN>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGTextPathElement : org.w3c.dom.svg.SVGTextContentElement, org.w3c.dom.svg.SVGURIReference {
    /*primary*/ public constructor SVGTextPathElement()
    public open val method: org.w3c.dom.svg.SVGAnimatedEnumeration
        public open fun <get-method>(): org.w3c.dom.svg.SVGAnimatedEnumeration
    public open val spacing: org.w3c.dom.svg.SVGAnimatedEnumeration
        public open fun <get-spacing>(): org.w3c.dom.svg.SVGAnimatedEnumeration
    public open val startOffset: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-startOffset>(): org.w3c.dom.svg.SVGAnimatedLength

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val LENGTHADJUST_SPACING: kotlin.Short
            public final fun <get-LENGTHADJUST_SPACING>(): kotlin.Short
        public final val LENGTHADJUST_SPACINGANDGLYPHS: kotlin.Short
            public final fun <get-LENGTHADJUST_SPACINGANDGLYPHS>(): kotlin.Short
        public final val LENGTHADJUST_UNKNOWN: kotlin.Short
            public final fun <get-LENGTHADJUST_UNKNOWN>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXTPATH_METHODTYPE_ALIGN: kotlin.Short
            public final fun <get-TEXTPATH_METHODTYPE_ALIGN>(): kotlin.Short
        public final val TEXTPATH_METHODTYPE_STRETCH: kotlin.Short
            public final fun <get-TEXTPATH_METHODTYPE_STRETCH>(): kotlin.Short
        public final val TEXTPATH_METHODTYPE_UNKNOWN: kotlin.Short
            public final fun <get-TEXTPATH_METHODTYPE_UNKNOWN>(): kotlin.Short
        public final val TEXTPATH_SPACINGTYPE_AUTO: kotlin.Short
            public final fun <get-TEXTPATH_SPACINGTYPE_AUTO>(): kotlin.Short
        public final val TEXTPATH_SPACINGTYPE_EXACT: kotlin.Short
            public final fun <get-TEXTPATH_SPACINGTYPE_EXACT>(): kotlin.Short
        public final val TEXTPATH_SPACINGTYPE_UNKNOWN: kotlin.Short
            public final fun <get-TEXTPATH_SPACINGTYPE_UNKNOWN>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGTextPositioningElement : org.w3c.dom.svg.SVGTextContentElement {
    /*primary*/ public constructor SVGTextPositioningElement()
    public open val dx: org.w3c.dom.svg.SVGAnimatedLengthList
        public open fun <get-dx>(): org.w3c.dom.svg.SVGAnimatedLengthList
    public open val dy: org.w3c.dom.svg.SVGAnimatedLengthList
        public open fun <get-dy>(): org.w3c.dom.svg.SVGAnimatedLengthList
    public open val rotate: org.w3c.dom.svg.SVGAnimatedNumberList
        public open fun <get-rotate>(): org.w3c.dom.svg.SVGAnimatedNumberList
    public open val x: org.w3c.dom.svg.SVGAnimatedLengthList
        public open fun <get-x>(): org.w3c.dom.svg.SVGAnimatedLengthList
    public open val y: org.w3c.dom.svg.SVGAnimatedLengthList
        public open fun <get-y>(): org.w3c.dom.svg.SVGAnimatedLengthList

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val LENGTHADJUST_SPACING: kotlin.Short
            public final fun <get-LENGTHADJUST_SPACING>(): kotlin.Short
        public final val LENGTHADJUST_SPACINGANDGLYPHS: kotlin.Short
            public final fun <get-LENGTHADJUST_SPACINGANDGLYPHS>(): kotlin.Short
        public final val LENGTHADJUST_UNKNOWN: kotlin.Short
            public final fun <get-LENGTHADJUST_UNKNOWN>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGTitleElement : org.w3c.dom.svg.SVGElement {
    /*primary*/ public constructor SVGTitleElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGTransform {
    /*primary*/ public constructor SVGTransform()
    public open val angle: kotlin.Float
        public open fun <get-angle>(): kotlin.Float
    public open val matrix: org.w3c.dom.DOMMatrix
        public open fun <get-matrix>(): org.w3c.dom.DOMMatrix
    public open val type: kotlin.Short
        public open fun <get-type>(): kotlin.Short
    public final fun setMatrix(/*0*/ matrix: org.w3c.dom.DOMMatrixReadOnly): kotlin.Unit
    public final fun setRotate(/*0*/ angle: kotlin.Float, /*1*/ cx: kotlin.Float, /*2*/ cy: kotlin.Float): kotlin.Unit
    public final fun setScale(/*0*/ sx: kotlin.Float, /*1*/ sy: kotlin.Float): kotlin.Unit
    public final fun setSkewX(/*0*/ angle: kotlin.Float): kotlin.Unit
    public final fun setSkewY(/*0*/ angle: kotlin.Float): kotlin.Unit
    public final fun setTranslate(/*0*/ tx: kotlin.Float, /*1*/ ty: kotlin.Float): kotlin.Unit

    public companion object Companion {
        public final val SVG_TRANSFORM_MATRIX: kotlin.Short
            public final fun <get-SVG_TRANSFORM_MATRIX>(): kotlin.Short
        public final val SVG_TRANSFORM_ROTATE: kotlin.Short
            public final fun <get-SVG_TRANSFORM_ROTATE>(): kotlin.Short
        public final val SVG_TRANSFORM_SCALE: kotlin.Short
            public final fun <get-SVG_TRANSFORM_SCALE>(): kotlin.Short
        public final val SVG_TRANSFORM_SKEWX: kotlin.Short
            public final fun <get-SVG_TRANSFORM_SKEWX>(): kotlin.Short
        public final val SVG_TRANSFORM_SKEWY: kotlin.Short
            public final fun <get-SVG_TRANSFORM_SKEWY>(): kotlin.Short
        public final val SVG_TRANSFORM_TRANSLATE: kotlin.Short
            public final fun <get-SVG_TRANSFORM_TRANSLATE>(): kotlin.Short
        public final val SVG_TRANSFORM_UNKNOWN: kotlin.Short
            public final fun <get-SVG_TRANSFORM_UNKNOWN>(): kotlin.Short
    }
}

public abstract external class SVGTransformList {
    /*primary*/ public constructor SVGTransformList()
    public open val length: kotlin.Int
        public open fun <get-length>(): kotlin.Int
    public open val numberOfItems: kotlin.Int
        public open fun <get-numberOfItems>(): kotlin.Int
    public final fun appendItem(/*0*/ newItem: org.w3c.dom.svg.SVGTransform): org.w3c.dom.svg.SVGTransform
    public final fun clear(): kotlin.Unit
    public final fun consolidate(): org.w3c.dom.svg.SVGTransform?
    public final fun createSVGTransformFromMatrix(/*0*/ matrix: org.w3c.dom.DOMMatrixReadOnly): org.w3c.dom.svg.SVGTransform
    public final fun getItem(/*0*/ index: kotlin.Int): org.w3c.dom.svg.SVGTransform
    public final fun initialize(/*0*/ newItem: org.w3c.dom.svg.SVGTransform): org.w3c.dom.svg.SVGTransform
    public final fun insertItemBefore(/*0*/ newItem: org.w3c.dom.svg.SVGTransform, /*1*/ index: kotlin.Int): org.w3c.dom.svg.SVGTransform
    public final fun removeItem(/*0*/ index: kotlin.Int): org.w3c.dom.svg.SVGTransform
    public final fun replaceItem(/*0*/ newItem: org.w3c.dom.svg.SVGTransform, /*1*/ index: kotlin.Int): org.w3c.dom.svg.SVGTransform
}

public external interface SVGURIReference {
    public abstract val href: org.w3c.dom.svg.SVGAnimatedString
        public abstract fun <get-href>(): org.w3c.dom.svg.SVGAnimatedString
}

public external interface SVGUnitTypes {

    public companion object Companion {
        public final val SVG_UNIT_TYPE_OBJECTBOUNDINGBOX: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_OBJECTBOUNDINGBOX>(): kotlin.Short
        public final val SVG_UNIT_TYPE_UNKNOWN: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_UNKNOWN>(): kotlin.Short
        public final val SVG_UNIT_TYPE_USERSPACEONUSE: kotlin.Short
            public final fun <get-SVG_UNIT_TYPE_USERSPACEONUSE>(): kotlin.Short
    }
}

public abstract external class SVGUnknownElement : org.w3c.dom.svg.SVGGraphicsElement {
    /*primary*/ public constructor SVGUnknownElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGUseElement : org.w3c.dom.svg.SVGGraphicsElement, org.w3c.dom.svg.SVGURIReference {
    /*primary*/ public constructor SVGUseElement()
    public open val animatedInstanceRoot: org.w3c.dom.svg.SVGElement?
        public open fun <get-animatedInstanceRoot>(): org.w3c.dom.svg.SVGElement?
    public open val height: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-height>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val instanceRoot: org.w3c.dom.svg.SVGElement?
        public open fun <get-instanceRoot>(): org.w3c.dom.svg.SVGElement?
    public open val width: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-width>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val x: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-x>(): org.w3c.dom.svg.SVGAnimatedLength
    public open val y: org.w3c.dom.svg.SVGAnimatedLength
        public open fun <get-y>(): org.w3c.dom.svg.SVGAnimatedLength

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public open external class SVGUseElementShadowRoot : org.w3c.dom.ShadowRoot {
    /*primary*/ public constructor SVGUseElementShadowRoot()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public abstract external class SVGViewElement : org.w3c.dom.svg.SVGElement, org.w3c.dom.svg.SVGFitToViewBox, org.w3c.dom.svg.SVGZoomAndPan {
    /*primary*/ public constructor SVGViewElement()

    public companion object Companion {
        public final val ATTRIBUTE_NODE: kotlin.Short
            public final fun <get-ATTRIBUTE_NODE>(): kotlin.Short
        public final val CDATA_SECTION_NODE: kotlin.Short
            public final fun <get-CDATA_SECTION_NODE>(): kotlin.Short
        public final val COMMENT_NODE: kotlin.Short
            public final fun <get-COMMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_FRAGMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_FRAGMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_NODE: kotlin.Short
            public final fun <get-DOCUMENT_NODE>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINED_BY: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINED_BY>(): kotlin.Short
        public final val DOCUMENT_POSITION_CONTAINS: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_CONTAINS>(): kotlin.Short
        public final val DOCUMENT_POSITION_DISCONNECTED: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_DISCONNECTED>(): kotlin.Short
        public final val DOCUMENT_POSITION_FOLLOWING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_FOLLOWING>(): kotlin.Short
        public final val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC>(): kotlin.Short
        public final val DOCUMENT_POSITION_PRECEDING: kotlin.Short
            public final fun <get-DOCUMENT_POSITION_PRECEDING>(): kotlin.Short
        public final val DOCUMENT_TYPE_NODE: kotlin.Short
            public final fun <get-DOCUMENT_TYPE_NODE>(): kotlin.Short
        public final val ELEMENT_NODE: kotlin.Short
            public final fun <get-ELEMENT_NODE>(): kotlin.Short
        public final val ENTITY_NODE: kotlin.Short
            public final fun <get-ENTITY_NODE>(): kotlin.Short
        public final val ENTITY_REFERENCE_NODE: kotlin.Short
            public final fun <get-ENTITY_REFERENCE_NODE>(): kotlin.Short
        public final val NOTATION_NODE: kotlin.Short
            public final fun <get-NOTATION_NODE>(): kotlin.Short
        public final val PROCESSING_INSTRUCTION_NODE: kotlin.Short
            public final fun <get-PROCESSING_INSTRUCTION_NODE>(): kotlin.Short
        public final val SVG_ZOOMANDPAN_DISABLE: kotlin.Short
            public final fun <get-SVG_ZOOMANDPAN_DISABLE>(): kotlin.Short
        public final val SVG_ZOOMANDPAN_MAGNIFY: kotlin.Short
            public final fun <get-SVG_ZOOMANDPAN_MAGNIFY>(): kotlin.Short
        public final val SVG_ZOOMANDPAN_UNKNOWN: kotlin.Short
            public final fun <get-SVG_ZOOMANDPAN_UNKNOWN>(): kotlin.Short
        public final val TEXT_NODE: kotlin.Short
            public final fun <get-TEXT_NODE>(): kotlin.Short
    }
}

public external interface SVGZoomAndPan {
    public abstract var zoomAndPan: kotlin.Short
        public abstract fun <get-zoomAndPan>(): kotlin.Short
        public abstract fun <set-zoomAndPan>(/*0*/ <set-?>: kotlin.Short): kotlin.Unit

    public companion object Companion {
        public final val SVG_ZOOMANDPAN_DISABLE: kotlin.Short
            public final fun <get-SVG_ZOOMANDPAN_DISABLE>(): kotlin.Short
        public final val SVG_ZOOMANDPAN_MAGNIFY: kotlin.Short
            public final fun <get-SVG_ZOOMANDPAN_MAGNIFY>(): kotlin.Short
        public final val SVG_ZOOMANDPAN_UNKNOWN: kotlin.Short
            public final fun <get-SVG_ZOOMANDPAN_UNKNOWN>(): kotlin.Short
    }
}

public open external class ShadowAnimation {
    /*primary*/ public constructor ShadowAnimation(/*0*/ source: dynamic, /*1*/ newTarget: dynamic)
    public open val sourceAnimation: dynamic
        public open fun <get-sourceAnimation>(): dynamic
}