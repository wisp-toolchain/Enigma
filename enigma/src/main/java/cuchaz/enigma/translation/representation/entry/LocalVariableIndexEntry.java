package cuchaz.enigma.translation.representation.entry;

import cuchaz.enigma.source.RenamableTokenType;
import cuchaz.enigma.translation.TranslateResult;
import cuchaz.enigma.translation.Translator;
import cuchaz.enigma.translation.mapping.EntryMapping;

import javax.annotation.Nonnull;

public class LocalVariableIndexEntry extends LocalVariableEntry {
	public LocalVariableIndexEntry(MethodEntry parent, int index, String name, boolean parameter, String javadoc) {
		super(parent, index, name, parameter, javadoc);
	}

	@Override
	protected TranslateResult<LocalVariableEntry> extendedTranslate(Translator translator, @Nonnull EntryMapping mapping) {
		String translatedName = mapping.targetName() != null ? mapping.targetName() : name;
		String javadoc = mapping.javadoc();
		return TranslateResult.of(mapping.targetName() == null ? RenamableTokenType.OBFUSCATED : RenamableTokenType.DEOBFUSCATED, new LocalVariableIndexEntry(parent, index, translatedName, parameter, javadoc));
	}

	@Override
	public LocalVariableEntry withName(String name) {
		return new LocalVariableIndexEntry(parent, index, name, parameter, javadocs);
	}

	@Override
	public LocalVariableEntry withParent(MethodEntry parent) {
		return new LocalVariableIndexEntry(parent, index, name, parameter, javadocs);
	}
}