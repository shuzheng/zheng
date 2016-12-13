/**
 * SyntaxHighlighter
 * http://alexgorbatchev.com/
 *
 * SyntaxHighlighter is donationware. If you are using it, please donate.
 * http://alexgorbatchev.com/wiki/SyntaxHighlighter:Donate
 *
 * @version
 * 2.1.382 (June 24 2010)
 * 
 * @copyright
 * Copyright (C) 2004-2009 Alex Gorbatchev.
 *
 * @license
 * This file is part of SyntaxHighlighter.
 * 
 * SyntaxHighlighter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * SyntaxHighlighter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SyntaxHighlighter.  If not, see <http://www.gnu.org/copyleft/lesser.html>.
 */
SyntaxHighlighter.brushes.JavaFX = function()
{
	// Contributed by Patrick Webster
	// http://patrickwebster.blogspot.com/2009/04/javafx-brush-for-syntaxhighlighter.html
	var datatypes =	'Boolean Byte Character Double Duration '
					+ 'Float Integer Long Number Short String Void'
					;

	var keywords = 'abstract after and as assert at before bind bound break catch class '
					+ 'continue def delete else exclusive extends false finally first for from '
					+ 'function if import in indexof init insert instanceof into inverse last '
					+ 'lazy mixin mod nativearray new not null on or override package postinit '
					+ 'protected public public-init public-read replace return reverse sizeof '
					+ 'step super then this throw true try tween typeof var where while with '
					+ 'attribute let private readonly static trigger'
					;

	this.regexList = [
		{ regex: SyntaxHighlighter.regexLib.singleLineCComments,	css: 'comments' },
		{ regex: SyntaxHighlighter.regexLib.multiLineCComments,		css: 'comments' },
		{ regex: SyntaxHighlighter.regexLib.singleQuotedString,		css: 'string' },
		{ regex: SyntaxHighlighter.regexLib.doubleQuotedString,		css: 'string' },
		{ regex: /(-?\.?)(\b(\d*\.?\d+|\d+\.?\d*)(e[+-]?\d+)?|0x[a-f\d]+)\b\.?/gi, css: 'color2' },	// numbers
		{ regex: new RegExp(this.getKeywords(datatypes), 'gm'),		css: 'variable' },	// datatypes
		{ regex: new RegExp(this.getKeywords(keywords), 'gm'),		css: 'keyword' }
	];
	this.forHtmlScript(SyntaxHighlighter.regexLib.aspScriptTags);
};

SyntaxHighlighter.brushes.JavaFX.prototype = new SyntaxHighlighter.Highlighter();
SyntaxHighlighter.brushes.JavaFX.aliases = ['jfx', 'javafx'];
