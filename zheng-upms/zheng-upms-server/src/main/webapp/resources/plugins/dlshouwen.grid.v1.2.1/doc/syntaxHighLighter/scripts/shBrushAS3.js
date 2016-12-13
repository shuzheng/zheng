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
SyntaxHighlighter.brushes.AS3 = function()
{
	// Created by Peter Atoria @ http://iAtoria.com
	
	var inits 	 =  'class interface function package';
	
	var keywords =	'-Infinity ...rest Array as AS3 Boolean break case catch const continue Date decodeURI ' + 
					'decodeURIComponent default delete do dynamic each else encodeURI encodeURIComponent escape ' + 
					'extends false final finally flash_proxy for get if implements import in include Infinity ' + 
					'instanceof int internal is isFinite isNaN isXMLName label namespace NaN native new null ' + 
					'Null Number Object object_proxy override parseFloat parseInt private protected public ' + 
					'return set static String super switch this throw true try typeof uint undefined unescape ' + 
					'use void while with'
					;
	
	this.regexList = [
		{ regex: SyntaxHighlighter.regexLib.singleLineCComments,	css: 'comments' },		// one line comments
		{ regex: SyntaxHighlighter.regexLib.multiLineCComments,		css: 'comments' },		// multiline comments
		{ regex: SyntaxHighlighter.regexLib.doubleQuotedString,		css: 'string' },		// double quoted strings
		{ regex: SyntaxHighlighter.regexLib.singleQuotedString,		css: 'string' },		// single quoted strings
		{ regex: /\b([\d]+(\.[\d]+)?|0x[a-f0-9]+)\b/gi,				css: 'value' },			// numbers
		{ regex: new RegExp(this.getKeywords(inits), 'gm'),			css: 'color3' },		// initializations
		{ regex: new RegExp(this.getKeywords(keywords), 'gm'),		css: 'keyword' },		// keywords
		{ regex: new RegExp('var', 'gm'),							css: 'variable' },		// variable
		{ regex: new RegExp('trace', 'gm'),							css: 'color1' }			// trace
		];
	
	this.forHtmlScript(SyntaxHighlighter.regexLib.scriptScriptTags);
};

SyntaxHighlighter.brushes.AS3.prototype	= new SyntaxHighlighter.Highlighter();
SyntaxHighlighter.brushes.AS3.aliases	= ['actionscript3', 'as3'];
