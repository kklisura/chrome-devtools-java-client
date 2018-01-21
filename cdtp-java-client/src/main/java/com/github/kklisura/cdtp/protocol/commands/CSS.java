package com.github.kklisura.cdtp.protocol.commands;

import com.github.kklisura.cdtp.protocol.annotations.Experimental;
import com.github.kklisura.cdtp.protocol.annotations.Returns;
import com.github.kklisura.cdtp.protocol.types.css.BackgroundColors;
import com.github.kklisura.cdtp.protocol.types.css.CSSComputedStyleProperty;
import com.github.kklisura.cdtp.protocol.types.css.CSSMedia;
import com.github.kklisura.cdtp.protocol.types.css.CSSRule;
import com.github.kklisura.cdtp.protocol.types.css.CSSStyle;
import com.github.kklisura.cdtp.protocol.types.css.ForcedPseudoClasses;
import com.github.kklisura.cdtp.protocol.types.css.InlineStylesForNode;
import com.github.kklisura.cdtp.protocol.types.css.MatchedStylesForNode;
import com.github.kklisura.cdtp.protocol.types.css.PlatformFontUsage;
import com.github.kklisura.cdtp.protocol.types.css.RuleUsage;
import com.github.kklisura.cdtp.protocol.types.css.SelectorList;
import com.github.kklisura.cdtp.protocol.types.css.SourceRange;
import com.github.kklisura.cdtp.protocol.types.css.StyleDeclarationEdit;
import com.github.kklisura.cdtp.protocol.types.css.Value;
import java.util.List;

/**
 * This domain exposes CSS read/write operations. All CSS objects (stylesheets, rules, and styles) have an associated <code>id</code> used in subsequent operations on the related object. Each object type has a specific <code>id</code> structure, and those are not interchangeable between objects of different kinds. CSS objects can be loaded using the <code>get*ForNode()</code> calls (which accept a DOM node id). A client can also keep track of stylesheets via the <code>styleSheetAdded</code>/<code>styleSheetRemoved</code> events and subsequently load the required stylesheet contents using the <code>getStyleSheet[Text]()</code> methods.
 */
@Experimental
public interface CSS {

	/**
	 * Enables the CSS agent for the given page. Clients should not assume that the CSS agent has been enabled until the result of this command is received.
	 */
	void enable();

	/**
	 * Disables the CSS agent for the given page.
	 */
	void disable();

	/**
	 * Returns requested styles for a DOM node identified by <code>nodeId</code>.
	 */
	MatchedStylesForNode getMatchedStylesForNode(Integer nodeId);

	/**
	 * Returns the styles defined inline (explicitly in the "style" attribute and implicitly, using DOM attributes) for a DOM node identified by <code>nodeId</code>.
	 */
	InlineStylesForNode getInlineStylesForNode(Integer nodeId);

	/**
	 * Returns the computed style for a DOM node identified by <code>nodeId</code>.
	 */
	@Returns("computedStyle")
	List<CSSComputedStyleProperty> getComputedStyleForNode(Integer nodeId);

	/**
	 * Requests information about platform fonts which we used to render child TextNodes in the given node.
	 */
	@Experimental
	@Returns("fonts")
	List<PlatformFontUsage> getPlatformFontsForNode(Integer nodeId);

	/**
	 * Returns the current textual content and the URL for a stylesheet.
	 */
	@Returns("text")
	String getStyleSheetText(String styleSheetId);

	/**
	 * Returns all class names from specified stylesheet.
	 */
	@Experimental
	@Returns("classNames")
	List<String> collectClassNames(String styleSheetId);

	/**
	 * Sets the new stylesheet text.
	 */
	@Returns("sourceMapURL")
	String setStyleSheetText(String styleSheetId, String text);

	/**
	 * Modifies the rule selector.
	 */
	@Returns("selectorList")
	SelectorList setRuleSelector(String styleSheetId, SourceRange range, String selector);

	/**
	 * Modifies the keyframe rule key text.
	 */
	@Returns("keyText")
	Value setKeyframeKey(String styleSheetId, SourceRange range, String keyText);

	/**
	 * Applies specified style edits one after another in the given order.
	 */
	@Returns("styles")
	List<CSSStyle> setStyleTexts(List<StyleDeclarationEdit> edits);

	/**
	 * Modifies the rule selector.
	 */
	@Returns("media")
	CSSMedia setMediaText(String styleSheetId, SourceRange range, String text);

	/**
	 * Creates a new special "via-inspector" stylesheet in the frame with given <code>frameId</code>.
	 */
	@Returns("styleSheetId")
	String createStyleSheet(String frameId);

	/**
	 * Inserts a new rule with the given <code>ruleText</code> in a stylesheet with given <code>styleSheetId</code>, at the position specified by <code>location</code>.
	 */
	@Returns("rule")
	CSSRule addRule(String styleSheetId, String ruleText, SourceRange location);

	/**
	 * Ensures that the given node will have specified pseudo-classes whenever its style is computed by the browser.
	 */
	void forcePseudoState(Integer nodeId, List<ForcedPseudoClasses> forcedPseudoClasses);

	/**
	 * Returns all media queries parsed by the rendering engine.
	 */
	@Experimental
	@Returns("medias")
	List<CSSMedia> getMediaQueries();

	/**
	 * Find a rule with the given active property for the given node and set the new value for this property
	 */
	@Experimental
	void setEffectivePropertyValueForNode(Integer nodeId, String propertyName, String value);

	@Experimental
	BackgroundColors getBackgroundColors(Integer nodeId);

	/**
	 * Enables the selector recording.
	 */
	@Experimental
	void startRuleUsageTracking();

	/**
	 * Obtain list of rules that became used since last call to this method (or since start of coverage instrumentation)
	 */
	@Experimental
	@Returns("coverage")
	List<RuleUsage> takeCoverageDelta();

	/**
	 * The list of rules with an indication of whether these were used
	 */
	@Experimental
	@Returns("ruleUsage")
	List<RuleUsage> stopRuleUsageTracking();
}
