package com.credenceid.issuance.service.domain.usecase.digitalid.generate;

import com.credenceid.identity.iso18013.DataIdentifier;
import com.credenceid.identity.iso18013.mdoc.IssuerSignedItem;
import com.credenceid.issuance.service.domain.model.PersonalData;

import java.util.List;

public class BuildIssuerSignedItemsUseCaseImpl implements BuildIssuerSignedItemsUseCase {
    @Override
    public List<IssuerSignedItem> execute(PersonalData personalData, List<byte[]> randoms) {
        int index = 0;
        // Family name
        IssuerSignedItem familyNameIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.FamilyName.value, personalData.getFamilyName());

        index++;

        // Given name
        IssuerSignedItem givenNameIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.GivenNames.value, personalData.getGivenNames());

        index++;

        // Issuing authority
        IssuerSignedItem issuingAuthorityIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.IssuingAuthority.value, personalData.getIssuer());

        index++;

        // Issuing country
        IssuerSignedItem issuingCountryIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.IssuingCountry.value, personalData.getIssuer());

        index++;

        // Document number
        IssuerSignedItem documentNumberIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.DocumentNumber.value, personalData.getDocumentNumber());

        index++;

        // Date of birth
        IssuerSignedItem dateOfBirthIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.BirthDate.value, personalData.getDateOfBirth());

        index++;

        // Sex
        IssuerSignedItem sexIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.Sex.value, personalData.getSex().getValue());

        index++;

        // Date of expiry
        IssuerSignedItem dateOfExpiryIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.ExpiryDate.value, personalData.getDateOfExpiry());

        index++;

        // Portrait
        IssuerSignedItem portraitIssuerSignedItem =
                new IssuerSignedItem(index, randoms.get(index), DataIdentifier.Portrait.value, personalData.getPortrait());

        return List.of(
                familyNameIssuerSignedItem,
                givenNameIssuerSignedItem,
                issuingAuthorityIssuerSignedItem,
                issuingCountryIssuerSignedItem,
                documentNumberIssuerSignedItem,
                dateOfBirthIssuerSignedItem,
                sexIssuerSignedItem,
                dateOfExpiryIssuerSignedItem,
                portraitIssuerSignedItem
        );
    }
}
